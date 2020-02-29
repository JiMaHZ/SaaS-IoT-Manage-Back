package org.springblade.modules.iot.service;

import org.springblade.modules.iot.entity.Device;

import java.util.List;

public interface IDeviceService {
	/**
	 * 获取已绑定deviceId的设备地理位置
	 * 【all】
	 * @return
	 */
	List<Device> getDeviceLocationList();

	/**
	 * 获取已绑定deviceId的设备地理位置
	 * @param deviceType
	 * @return
	 */
	List<Device> getDeviceLocationList(String deviceType);

	/**
	 * 获取未绑定设备的列表
	 * @return
	 */
	List<Device> getDevicesWithoutBindList();

	/**
	 * 绑定实际设备与deviceId
	 * @param gatewayAddress
	 * @param deviceId
	 * @return
	 */
	Boolean bindDeviceId(String gatewayAddress, String deviceId);

	/**
	 * 解除设备的id绑定
	 * @param gatewayAddress
	 * @return
	 */
	Boolean unbindDeviceId(String gatewayAddress);

	Device getDeviceByDeviceId(String deviceId);
}
