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
import org.springblade.modules.iot.exception.CannotFindException;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.iot.entity.Ashbin;
import org.springblade.modules.iot.vo.AshbinVO;
import org.springblade.modules.iot.wrapper.AshbinWrapper;
import org.springblade.modules.iot.service.IAshbinService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author Blade
 * @since 2020-01-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("iot/ashbin")
@Api(value = "", tags = "接口")
public class AshbinController extends BladeController {

	private IAshbinService ashbinService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ashbin")
	public R<AshbinVO> detail(Ashbin ashbin) {
		Ashbin detail = ashbinService.getOne(Condition.getQueryWrapper(ashbin));
		return R.data(AshbinWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ashbin")
	public R<IPage<AshbinVO>> list(Ashbin ashbin, Query query) {
		IPage<Ashbin> pages = ashbinService.page(Condition.getPage(query), Condition.getQueryWrapper(ashbin));
		return R.data(AshbinWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入ashbin")
	public R<IPage<AshbinVO>> page(AshbinVO ashbin, Query query) {
		IPage<AshbinVO> pages = ashbinService.selectAshbinPage(Condition.getPage(query), ashbin);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入ashbin")
	public R save(@Valid @RequestBody Ashbin ashbin) {
		return R.status(ashbinService.save(ashbin));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入ashbin")
	public R update(@Valid @RequestBody Ashbin ashbin) {
		return R.status(ashbinService.updateById(ashbin));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入ashbin")
	public R submit(@Valid @RequestBody Ashbin ashbin) {
		return R.status(ashbinService.saveOrUpdate(ashbin));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ashbinService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 垃圾桶状态
	 */
	@GetMapping("/status")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "状态", notes = "传入ashbin")
	public R<AshbinVO> status(String deviceId) {
//		Ashbin detail = ashbinService.getOne(Condition.getQueryWrapper(ashbin));
		Ashbin detail = null;
		try {
			detail = ashbinService.getAshbinStatus(deviceId);
		}catch (CannotFindException e){
			return R.fail(204,e.getMessage());
		}

		return R.data(AshbinWrapper.build().entityVO(detail));
	}


	
}
