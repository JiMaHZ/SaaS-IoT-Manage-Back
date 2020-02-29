package org.springblade.modules.iot.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.iot.entity.Device;

import java.util.List;

//@DS("iot")
public interface DeviceMapper extends BaseMapper<Device> {

	Device getDeviceById(String deviceId);

	/**
	 * 获取有绑定Id的设备列表
	 * @return
	 */
	List<Device> getDeviceListWithBindId();

	/**
	 * 通过设备类型来获得已绑定Id的设备列表
	 * @param deviceType
	 * @return
	 */
	List<Device> getDeviceListWithBindIdAndType(String deviceType);

	List<Device> getDeviceListWithoutBindId();

	int setDeviceId(@Param("gatewayAddress")String gatewayAddress,
						 @Param("deviceId") String deviceId);


}
