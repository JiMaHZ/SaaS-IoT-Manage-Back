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
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.vo.UploadDataVO;
import org.springblade.modules.iot.wrapper.UploadDataWrapper;
import org.springblade.modules.iot.service.IUploadDataService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 *  控制器
 *
 * @author Blade
 * @since 2019-12-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/iotPlatform/uploaddata")
@Api(value = "", tags = "接口")
public class UploadDataController extends BladeController {

	private IUploadDataService uploadDataService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入uploadData")
	public R<UploadDataVO> detail(UploadData uploadData) {
		UploadData detail = uploadDataService.getOne(Condition.getQueryWrapper(uploadData));
		return R.data(UploadDataWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入uploadData")
	public R<IPage<UploadDataVO>> list(UploadData uploadData, Query query) {
		query.setDescs("id");
		IPage<UploadData> pages = uploadDataService.page(Condition.getPage(query), Condition.getQueryWrapper(uploadData));
		return R.data(UploadDataWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入uploadData")
	public R<IPage<UploadDataVO>> page(UploadDataVO uploadData, Query query) {
		IPage<UploadDataVO> pages = uploadDataService.selectUploadDataPage(Condition.getPage(query), uploadData);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入uploadData")
	public R save(@Valid @RequestBody UploadData uploadData) {
		return R.status(uploadDataService.save(uploadData));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入uploadData")
	public R update(@Valid @RequestBody UploadData uploadData) {
		return R.status(uploadDataService.updateById(uploadData));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入uploadData")
	public R submit(@Valid @RequestBody UploadData uploadData) {
		return R.status(uploadDataService.saveOrUpdate(uploadData));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
//		return R.status(uploadDataService.deleteLogic(Func.toIntList(ids)));
		return R.status(uploadDataService.removeByIds(Func.toIntList(ids)));
	}

	@GetMapping("/oneByIdAndKey")
	public R<UploadDataVO> getBindOne(@RequestParam(value = "deviceId") String deviceId,
								  @RequestParam(value = "deviceKey") String deviceKey) {
		UploadData uploadData = null;
		try{
			uploadData = uploadDataService.getDataByDeviceIdAndKey(deviceId, deviceKey);
		}catch (Exception e){
			R.fail(e.getMessage());
		}
		return R.data(UploadDataWrapper.build().entityVO(uploadData));
	}

	@GetMapping("/listByIdAndKey")
	public R<List<UploadData>> getBindList(@RequestParam(value = "deviceId") String deviceId,
									   @RequestParam(value = "deviceKey") List<String> deviceKey) {
		List<UploadData> uploadData = null;
		try{
			uploadData = uploadDataService.getDataListByDeviceIdAndKey(deviceId, deviceKey);
		}catch (Exception e){
			R.fail(e.getMessage());
		}
		return R.data(uploadData);
	}

	
}
