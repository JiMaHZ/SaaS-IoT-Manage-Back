package org.springblade.modules.iot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.entity.DeviceKey;
import org.springblade.modules.iot.entity.DeviceStatus;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.exception.CannotFindException;
import org.springblade.modules.iot.mapper.DeviceKeyMapper;
import org.springblade.modules.iot.service.IDeviceKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springblade.modules.iot.util.StringUtil.stringToArray;

@Service
@DS("iot")
public class DeviceKeyServiceImpl extends ServiceImpl<DeviceKeyMapper, DeviceKey> implements IDeviceKeyService {

	@Resource
	private DeviceKeyMapper deviceKeyMapper;

	@Autowired
	private UploadDataServiceImpl uploadDataService;

	@Resource
	private DeviceStatusServiceImpl deviceStatusService;

	@Override
	public boolean saveOrUpdateDeviceKey(DeviceKey deviceKey) {
		if (deviceKey == null) {
			return false;
		}
		if (deviceKey.getDeviceId() != null && deviceKey.getDeviceKey() != null) {
			if (deviceKeyMapper.getByIdandKey(deviceKey) != null) {
				return updateDeviceKey(deviceKey);
			} else {
				return saveDeviceKey(deviceKey);
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> getLatestList(String deviceId) {
		Map<String, Object> map = new HashMap<>();
		try {
			DeviceStatus device = deviceStatusService.getById(deviceId);
			if (device != null) {
				map.put("deviceName", device.getDeviceName());
			} else {
				map.put("deviceName", null);
			}
			List<DeviceKey> list = getLatestListAttr(deviceId);
			map.put("data", list);
		} catch (CannotFindException e) {
			throw new CannotFindException(e.getMessage());
		}
		return map;
	}

	@Override
	public List<DeviceKey> getLatestListAttr(String deviceId) {
		List<DeviceKey> listKey = deviceKeyMapper.getListKeyById(deviceId);
		try {
			getDeviceValueByKey(deviceId, listKey, "0900");
			getDeviceValueByKey(deviceId, listKey, "0B00");
		} catch (CannotFindException e) {
			throw new CannotFindException(e.getMessage());
		}
		return listKey;
	}

	public void getDeviceValueByKey(String deviceId, List<DeviceKey> deviceKeys, String key) {
		int keyindex = 0;
		String[] keyArr = null;
		int lower = Integer.parseInt(key, 16);
		int upper = lower + Integer.parseInt("1F", 16);
		try {
			UploadData uploadData = uploadDataService.getDataByDeviceIdAndKey(deviceId, key);
			if (uploadData != null) {
				keyArr = stringToArray(uploadData.getDeviceValue());
			}
			for (DeviceKey deviceKey : deviceKeys) {
				int tempKey = Integer.parseInt(deviceKey.getDeviceKey(), 16);
				if (keyArr != null && tempKey >= lower && tempKey <= upper) {
					keyindex = (tempKey - lower) / 4;
					if (keyindex < keyArr.length) {
						if (deviceKey.getMin() == null || deviceKey.getMin().trim().length() == 0) {
							deviceKey.setValue(keyArr[keyindex].trim());
						} else {
							DecimalFormat df;
							double result;
							String type = deviceKey.getDataType();
							int originLen = Integer.parseInt(deviceKey.getDataOriginWidth().trim());
							int min = Integer.parseInt(deviceKey.getMin().trim());
							int max = Integer.parseInt(deviceKey.getMax().trim());
							int originValue = Integer.parseInt(keyArr[keyindex].trim());
							System.out.println("  " + deviceKey.getDeviceKey() + " value: " + originValue);
							if (max == min) {
								result = min;
							} else {
								result = originValue * (originLen * 1.0 / (max - min)) + min;
							}
							if (type.equals("int")) {
								df = new DecimalFormat("0");
							} else {
								df = new DecimalFormat("0.00");
							}
							deviceKey.setValue(df.format(result));
						}
						deviceKey.setModifyTime(uploadData.getUploadTime());
					}
				}
				System.out.println("key:" + deviceKey.getDeviceKey() + " value: " + deviceKey.getValue());
			}
		} catch (CannotFindException e) {
			throw new CannotFindException(e.getMessage());
		} catch (Exception e) {
			throw new CannotFindException("未配置相关属性！");
		}


	}

	public boolean updateDeviceKey(DeviceKey deviceKey) {
		return retBool(deviceKeyMapper.updateDeviceKey(deviceKey));
	}

	public boolean saveDeviceKey(DeviceKey deviceKey) {
		return retBool(deviceKeyMapper.saveDeviceKey(deviceKey));
	}

}
