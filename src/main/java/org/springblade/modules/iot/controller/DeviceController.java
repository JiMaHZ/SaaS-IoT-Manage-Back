package org.springblade.modules.iot.controller;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.entity.Ashbin;
import org.springblade.modules.iot.entity.Device;
import org.springblade.modules.iot.exception.InsertFailedExeption;
import org.springblade.modules.iot.service.IDeviceService;
import org.springblade.modules.iot.vo.AshbinVO;
import org.springblade.modules.iot.wrapper.AshbinWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/iot/devices")
public class DeviceController {

	private IDeviceService deviceService;

	/**
	 * 所有有地址的设备列表
	 */
	@GetMapping("/locations")
	public List<Device> status() {
		return deviceService.getDeviceLocationList();
	}

	/**
	 * 某个deviceType下 有地址的设备列表
	 */
	@GetMapping("/locations/{deviceType}")
	public List<Device> status(@PathVariable String deviceType) {
		return deviceService.getDeviceLocationList(deviceType);
	}


	/**
	 * 获取未绑定设备地址
	 *
	 * @return
	 */
	@GetMapping("/noBindList")
	public List<Device> getBindList() {
		return deviceService.getDevicesWithoutBindList();
	}

	/**
	 * 绑定网关地址
	 *
	 * @param gatewayAddress
	 * @return
	 */
	@PostMapping("/bindGatewayAddress")
	@ResponseBody
	public R bindGatewayAddress(@RequestParam(value = "gatewayAddress") String gatewayAddress,
								@RequestParam(value = "deviceId") String deviceId) {
		boolean update = false;
		try {
			update = deviceService.bindDeviceId(gatewayAddress, deviceId);
		}catch (InsertFailedExeption e){
			return R.fail(e.getMessage());
		}
		return R.status(update);
	}

	/**
	 * 解除设备ID绑定
	 * @param gatewayAddress
	 * @return
	 */
	@PostMapping("/unbindGatewayAddress")
	@ResponseBody
	public R bindGatewayAddress(@RequestParam(value = "gatewayAddress") String gatewayAddress) {
		boolean update = deviceService.unbindDeviceId(gatewayAddress);
		return R.status(update);
	}

	@GetMapping("/ids/{deviceId}")
	public Device getDeviceByDeviceId(@PathVariable String deviceId) {
		return deviceService.getDeviceByDeviceId(deviceId);
	}



}
