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

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.iot.entity.DeviceAttribute;
import org.springblade.modules.iot.vo.DeviceAttributeVO;
import org.springblade.modules.iot.wrapper.DeviceAttributeWrapper;
import org.springblade.modules.iot.service.IDeviceAttributeService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author Blade
 * @since 2019-12-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/iot/deviceattribute")
@Api(value = "", tags = "接口")
public class DeviceAttributeController extends BladeController {

	private IDeviceAttributeService deviceAttributeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入deviceAttribute")
	public R<DeviceAttributeVO> detail(DeviceAttribute deviceAttribute) {
		DeviceAttribute detail = deviceAttributeService.getOne(Condition.getQueryWrapper(deviceAttribute));
		return R.data(DeviceAttributeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入deviceAttribute")
	public R<IPage<DeviceAttributeVO>> list(DeviceAttribute deviceAttribute, Query query) {
		IPage<DeviceAttribute> pages = deviceAttributeService.page(Condition.getPage(query), Condition.getQueryWrapper(deviceAttribute));
		return R.data(DeviceAttributeWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入deviceAttribute")
	public R<IPage<DeviceAttributeVO>> page(DeviceAttributeVO deviceAttribute, Query query) {
		IPage<DeviceAttributeVO> pages = deviceAttributeService.selectDeviceAttributePage(Condition.getPage(query), deviceAttribute);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入deviceAttribute")
	public R save(@Valid @RequestBody DeviceAttribute deviceAttribute) {
		return R.status(deviceAttributeService.save(deviceAttribute));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入deviceAttribute")
	public R update(@Valid @RequestBody DeviceAttribute deviceAttribute) {
		return R.status(deviceAttributeService.updateById(deviceAttribute));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入deviceAttribute")
	public R submit(@Valid @RequestBody DeviceAttribute deviceAttribute) {
		return R.status(deviceAttributeService.saveOrUpdate(deviceAttribute));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(deviceAttributeService.removeByIds(Func.toIntList(ids)));
	}

	
}
