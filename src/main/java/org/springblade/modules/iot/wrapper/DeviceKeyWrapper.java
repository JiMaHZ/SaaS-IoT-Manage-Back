package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.entity.DeviceKey;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.vo.DeviceKeyVO;
import org.springblade.modules.iot.vo.UploadDataVO;

public class DeviceKeyWrapper extends BaseEntityWrapper<DeviceKey, DeviceKeyVO> {

	public static DeviceKeyWrapper build() {
		return new DeviceKeyWrapper();
	}

	@Override
	public DeviceKeyVO entityVO(DeviceKey deviceKey) {
		DeviceKeyVO deviceKeyVO = BeanUtil.copy(deviceKey, DeviceKeyVO.class);

		return deviceKeyVO;
	}

}
