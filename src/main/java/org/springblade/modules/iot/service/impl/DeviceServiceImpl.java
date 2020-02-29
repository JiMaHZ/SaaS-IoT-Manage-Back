package org.springblade.modules.iot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.modules.iot.entity.Device;
import org.springblade.modules.iot.exception.InsertFailedExeption;
import org.springblade.modules.iot.mapper.DeviceMapper;
import org.springblade.modules.iot.service.IDeviceService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static org.springblade.modules.iot.util.gpsConvertUtil.getDevicesWithLocation;

@Service
@DS("iot")
public class DeviceServiceImpl implements IDeviceService {

	private final static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Resource
	private DeviceMapper deviceMapper;

	@Override
	public List<Device> getDeviceLocationList() {
		List<Device> locationList = deviceMapper.getDeviceListWithBindId();

		//对于已存在地址的 修改gps格式
		getDevicesWithLocation(locationList);
		return locationList;
	}

	/**
	 * 获取有类型的设备列表
	 * @param deviceType
	 * @return
	 */
	@Override
	public List<Device> getDeviceLocationList(String deviceType) {
		List<Device> deviceList = deviceMapper.getDeviceListWithBindIdAndType(deviceType);
		//对于已存在地址的 修改gps格式
		getDevicesWithLocation(deviceList);
		return deviceList;
	}

	@Override
	public List<Device> getDevicesWithoutBindList(){
		List<Device> locationList = deviceMapper.getDeviceListWithoutBindId();
		//对于已存在地址的 修改gps格式
		getDevicesWithLocation(locationList);
		return locationList;
	}

	@Override
	public Boolean bindDeviceId(String gatewayAddress, String deviceId) {
		boolean result = false;
		try{
			int update = deviceMapper.setDeviceId(gatewayAddress, deviceId);
			if(update>0){
				result = true;
			}
		}catch (DuplicateKeyException e){
			logger.error("重复绑定:"+e.getMessage());
			throw new InsertFailedExeption("设备重复绑定！");
		}catch (Exception e){
			logger.error("绑定异常:"+e.getMessage());
			throw new InsertFailedExeption("绑定异常！");
		}
		return result;
	}

	@Override
	public Boolean unbindDeviceId(String gatewayAddress) {
		boolean result = false;
		try{
			int update = deviceMapper.setDeviceId(gatewayAddress, null);
			if(update>0){
				result = true;
			}
		}catch (Exception e){
			logger.error("解绑异常:"+e.getMessage());
			throw new InsertFailedExeption("解绑异常！");
		}
		return result;
	}

	@Override
	public Device getDeviceByDeviceId(String deviceId) {
		return deviceMapper.getDeviceById(deviceId);
	}



}
