package org.springblade.modules.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.entity.DeviceKey;

import java.util.List;

public interface DeviceKeyMapper extends BaseMapper<DeviceKey> {

	List<DeviceKey> getListKeyById(String deviceId);

	DeviceKey getByIdandKey(DeviceKey deviceKey);

	int saveDeviceKey(DeviceKey deviceKey);

	int updateDeviceKey(DeviceKey deviceKey);

}
