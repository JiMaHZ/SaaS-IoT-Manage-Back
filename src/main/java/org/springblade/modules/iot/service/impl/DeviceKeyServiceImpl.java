package org.springblade.modules.iot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.entity.DeviceKey;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.exception.CannotFindException;
import org.springblade.modules.iot.mapper.DeviceKeyMapper;
import org.springblade.modules.iot.service.IDeviceKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
	public Map<String,String> getLatestList(String deviceId) {

		return null;
	}

	@Override
	public List<DeviceKey> getLatestListAttr(String deviceId) {
		List<DeviceKey> listAttr = new ArrayList<>();
		//0900的上下限
		int upper0900 = Integer.parseInt("091F",16);
		int lower0900 = Integer.parseInt("0900",16);
		//0b00的上下限
		int upper0B00 = Integer.parseInt("0B1F",16);
		int lower0B00 = Integer.parseInt("0B00",16);
		int keyindex = 0;
		String[] key0900 = null;
		String[] key0B00 = null;
		try {
			UploadData key0900Str = uploadDataService.getDataByDeviceIdAndKey(deviceId, "0900");
			UploadData key0B00Str = uploadDataService.getDataByDeviceIdAndKey(deviceId, "0B00");

			if(key0900Str != null){
				key0900 = stringToArray(key0900Str.getDeviceValue());
			}
			if(key0B00Str != null){
				key0B00 = stringToArray(key0B00Str.getDeviceValue());
			}

			//key属性列表
			List<DeviceKey> listKey = deviceKeyMapper.getListKeyById(deviceId);
			for (DeviceKey deviceKey : listKey) {
				int key = Integer.parseInt(deviceKey.getDeviceKey(),16);
				if(key>= lower0900 && key <= upper0900){
					keyindex = (key - lower0900)/4;
					if(keyindex < key0900.length){
						deviceKey.setValue(key0900[keyindex].trim());
						deviceKey.setModifyTime(key0900Str.getUploadTime());
						listAttr.add(deviceKey);
					}
				}
				if(key>= lower0B00 && key <= upper0B00){
					keyindex = (key - lower0B00)/4;
					if(keyindex < key0B00.length){
						deviceKey.setValue(key0B00[keyindex].trim());
						deviceKey.setModifyTime(key0B00Str.getUploadTime());
						listAttr.add(deviceKey);
					}
				}
			}
		}catch (CannotFindException e){
			throw new CannotFindException(e.getMessage());
		}catch (Exception e){
			throw new CannotFindException("未配置相关属性！");
		}

		return listAttr;
	}

	public void getDeviceValueByKey(List<DeviceKey> deviceKeys,String key){

	}

	public boolean updateDeviceKey(DeviceKey deviceKey) {
		return retBool(deviceKeyMapper.updateDeviceKey(deviceKey));
	}

	public boolean saveDeviceKey(DeviceKey deviceKey) {
		return retBool(deviceKeyMapper.saveDeviceKey(deviceKey));
	}

}
