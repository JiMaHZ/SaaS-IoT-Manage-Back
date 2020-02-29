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
package org.springblade.modules.iot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.vo.UploadDataVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Blade
 * @since 2019-12-26
 */
public interface UploadDataMapper extends BaseMapper<UploadData> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param uploadData
	 * @return
	 */
	List<UploadDataVO> selectUploadDataPage(IPage page, UploadDataVO uploadData);

	/**
	 * 获取网关地址下最新的一条数据
	 *
	 * @param gatewayAddress
	 * @return
	 */
	UploadData getLatestDataByGatewayAddress(String gatewayAddress);

	/**
	 * 获取网关地址和key下最新的一条数据
	 *
	 * @param gatewayAddress
	 * @param deviceKey
	 * @return
	 */
	UploadData getLatestDataByGatewayAddressAndKey(@Param("gatewayAddress")String gatewayAddress,
												   @Param("deviceKey")String deviceKey);

}
