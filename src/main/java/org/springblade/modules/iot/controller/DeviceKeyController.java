package org.springblade.modules.iot.controller;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.entity.DeviceKey;
import org.springblade.modules.iot.entity.DeviceStatus;
import org.springblade.modules.iot.service.IDeviceKeyService;
import org.springblade.modules.iot.vo.DeviceKeyVO;
import org.springblade.modules.iot.vo.DeviceStatusVO;
import org.springblade.modules.iot.wrapper.DeviceStatusWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("iot/deviceKey")
public class DeviceKeyController {

	private IDeviceKeyService deviceKeyService;

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	public R submit(@Valid @RequestBody DeviceKey deviceKey) {
		return R.status(deviceKeyService.saveOrUpdateDeviceKey(deviceKey));
	}


	/**
	 * 详情
	 */
	@GetMapping("/listWithValue")
	public List<DeviceKey> getLatestValueList(String deviceId) {
//		DeviceKey detail = deviceStatusService.getOne(Condition.getQueryWrapper(deviceStatus));
//		return R.data();
		return deviceKeyService.getLatestListAttr(deviceId);
	}


}
