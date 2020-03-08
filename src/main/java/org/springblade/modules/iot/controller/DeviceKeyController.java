package org.springblade.modules.iot.controller;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.entity.DeviceKey;
import org.springblade.modules.iot.entity.DeviceStatus;
import org.springblade.modules.iot.exception.CannotFindException;
import org.springblade.modules.iot.service.IDeviceKeyService;
import org.springblade.modules.iot.vo.DeviceKeyVO;
import org.springblade.modules.iot.vo.DeviceStatusVO;
import org.springblade.modules.iot.wrapper.DeviceStatusWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("iot/deviceKey")
public class DeviceKeyController {

	private IDeviceKeyService deviceKeyService;

	/**
	 * 新增修改
	 * @param deviceKey
	 * @return
	 */
	@PostMapping("/submit")
	public R submit(@Valid @RequestBody DeviceKey deviceKey) {
		return R.status(deviceKeyService.saveOrUpdateDeviceKey(deviceKey));
	}

	/**
	 * 修改
	 * @param deviceKey
	 * @return
	 */
	@PostMapping("/update")
	public R update(@Valid @RequestBody DeviceKey deviceKey) {
		return R.status(deviceKeyService.updateDeviceKey(deviceKey));
	}

	/**
	 * 删除
	 * @param deviceId
	 * @param deviceKey
	 * @return
	 */
	@PostMapping("/remove")
	public R remove( @RequestParam String deviceId,@RequestParam String deviceKey) {
		try{
			return R.status(deviceKeyService.removeDeviceByIdAndKey(deviceId,deviceKey));
		}catch (Exception e){
			return R.fail(e.getMessage());
		}
	}


	/**
	 * 获取deviceId相关的key列表对应的属性（包括value）
	 * @param deviceId
	 * @return
	 */
	@GetMapping("/listWithValue")
	public R getLatestValueList(String deviceId) {
//		DeviceKey detail = deviceStatusService.getOne(Condition.getQueryWrapper(deviceStatus));
//		return R.data();
		List<DeviceKey> list = null;
		try{
			list = deviceKeyService.getLatestListAttr(deviceId);
		}catch (CannotFindException e){
			return R.fail(204,e.getMessage());
		}
		return R.data(list);
	}

	/**
	 * 获取deviceId相关的key列表对应的属性（包括value）以及设备名
	 * @param deviceId
	 * @return
	 */
	@GetMapping("/list")
	public R getLatestList(String deviceId){
		Map<String,Object> map = new HashMap<>();
		try{
			map = deviceKeyService.getLatestList(deviceId);
		}catch (CannotFindException e){
			return R.fail(204,e.getMessage());
		}
		return R.data(map);
	}

}
