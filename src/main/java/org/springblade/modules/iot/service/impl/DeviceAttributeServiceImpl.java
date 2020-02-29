/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.iot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.entity.DeviceAttribute;
import org.springblade.modules.iot.vo.DeviceAttributeVO;
import org.springblade.modules.iot.mapper.DeviceAttributeMapper;
import org.springblade.modules.iot.service.IDeviceAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2019-12-27
 */
@Service
@DS("iot")
public class DeviceAttributeServiceImpl extends ServiceImpl<DeviceAttributeMapper, DeviceAttribute> implements IDeviceAttributeService {

	@Override
	public IPage<DeviceAttributeVO> selectDeviceAttributePage(IPage<DeviceAttributeVO> page, DeviceAttributeVO deviceAttribute) {
		return page.setRecords(baseMapper.selectDeviceAttributePage(page, deviceAttribute));
	}

}
