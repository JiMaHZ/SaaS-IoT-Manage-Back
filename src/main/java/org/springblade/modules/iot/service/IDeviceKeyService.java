package org.springblade.modules.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.entity.DeviceKey;

import java.util.List;
import java.util.Map;

public interface IDeviceKeyService extends IService<DeviceKey> {
	boolean saveOrUpdateDeviceKey(DeviceKey deviceKey);

	/**
	 * 获取属性列表（含Key等参数）
	 * @param deviceId
	 * @return
	 */
	List<DeviceKey> getLatestListAttr(String deviceId);

	/**
	 * 获取属性列表 keyName value
	 * @param deviceId
	 * @return
	 */
	Map<String,String> getLatestList(String deviceId);
}
