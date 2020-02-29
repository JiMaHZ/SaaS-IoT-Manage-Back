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
package org.springblade.modules.iot.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.entity.DeviceStatus;
import org.springblade.modules.iot.vo.DeviceStatusVO;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Blade
 * @since 2019-12-27
 */
public class DeviceStatusWrapper extends BaseEntityWrapper<DeviceStatus, DeviceStatusVO>  {

    public static DeviceStatusWrapper build() {
        return new DeviceStatusWrapper();
    }

	@Override
	public DeviceStatusVO entityVO(DeviceStatus deviceStatus) {
		DeviceStatusVO deviceStatusVO = BeanUtil.copy(deviceStatus, DeviceStatusVO.class);

		return deviceStatusVO;
	}

}
