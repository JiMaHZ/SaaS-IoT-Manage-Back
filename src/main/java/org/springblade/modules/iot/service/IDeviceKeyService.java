package org.springblade.modules.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.entity.DeviceKey;

import java.util.List;
import java.util.Map;

public interface IDeviceKeyService extends IService<DeviceKey> {
	/**
	 * 创建或者更新设备Key的属性规则
	 * @param deviceKey
	 * @return
	 */
	boolean saveOrUpdateDeviceKey(DeviceKey deviceKey);

	/**
	 * 更新设备Key的属性规则
	 * @param deviceKey
	 * @return
	 */
	boolean updateDeviceKey(DeviceKey deviceKey);

	/**
	 *
	 * 删除某个key
	 * @param deviceId
	 * @param deviceKey
	 * @return
	 */
	boolean removeDeviceByIdAndKey(String deviceId,String deviceKey);

	/**
	 * 获取属性列表（含Key等参数）
	 * @param deviceId
	 * @return
	 */
	List<DeviceKey> getLatestListAttr(String deviceId);

	/**
	 * 获取属性列表 deviceName keyName value（包含设备名，无规则）
	 * @param deviceId
	 * @return
	 */
	Map<String, Object> getLatestList(String deviceId);

}
