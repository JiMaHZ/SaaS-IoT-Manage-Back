/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.iot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.iot.entity.DeviceStatus;
import org.springblade.modules.iot.vo.DeviceStatusVO;
import org.springblade.modules.iot.wrapper.DeviceStatusWrapper;
import org.springblade.modules.iot.service.IDeviceStatusService;
import org.springblade.core.boot.ctrl.BladeController;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 *  控制器
 *
 * @author Blade
 * @since 2019-12-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/iot/devicestatus")
@Api(value = "", tags = "接口")
public class DeviceStatusController extends BladeController {

	private IDeviceStatusService deviceStatusService;

	private final static Logger logger = LoggerFactory.getLogger(DeviceStatusController.class);

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入deviceStatus")
	public R<DeviceStatusVO> detail(DeviceStatus deviceStatus) {
		DeviceStatus detail = deviceStatusService.getOne(Condition.getQueryWrapper(deviceStatus));
		return R.data(DeviceStatusWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入deviceStatus")
	public R<IPage<DeviceStatusVO>> list(DeviceStatus deviceStatus, Query query) {
		IPage<DeviceStatus> pages = deviceStatusService.page(Condition.getPage(query), Condition.getQueryWrapper(deviceStatus));
		return R.data(DeviceStatusWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入deviceStatus")
	public R<IPage<DeviceStatusVO>> page(DeviceStatusVO deviceStatus, Query query) {
		IPage<DeviceStatusVO> pages = deviceStatusService.selectDeviceStatusPage(Condition.getPage(query), deviceStatus);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入deviceStatus")
	public R save(@Valid @RequestBody DeviceStatus deviceStatus) {
		R result = null;
		try {
			deviceStatus.setCreateTime(LocalDateTime.now());
			deviceStatus.setLastUpdateTime(null);
			boolean save = deviceStatusService.save(deviceStatus);
			if(save){
				result = R.success("插入成功");

			}
		}catch (DuplicateKeyException e){
//			result = "主键重复，插入失败";
			result = R.fail("主键重复，插入失败");
			logger.error("主键重复，插入失败");
		}catch (Exception e){
//			result = "其他异常";
			result = R.fail("其他异常");
			logger.error("其他异常");
		}
		return result;
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入deviceStatus")
	public R update(@Valid @RequestBody DeviceStatus deviceStatus) {
		return R.status(deviceStatusService.updateById(deviceStatus));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入deviceStatus")
	public R submit(@Valid @RequestBody DeviceStatus deviceStatus) {
		return R.status(deviceStatusService.saveOrUpdate(deviceStatus));
	}

	
	/**
	 * 删除 
	 */
//	@PostMapping("/remove")
//	@ApiOperationSupport(order = 8)
//	@ApiOperation(value = "删除", notes = "传入ids")
//	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
//		return R.status(deviceStatusService.removeByIds(Func.toIntList(ids)));
//	}
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String deviceId) {
		return R.status(deviceStatusService.removeById(deviceId));
	}




}
