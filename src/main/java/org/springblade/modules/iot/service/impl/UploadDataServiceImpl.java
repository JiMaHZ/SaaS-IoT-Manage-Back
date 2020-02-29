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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.entity.Device;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.exception.CannotFindException;
import org.springblade.modules.iot.mapper.DeviceMapper;
import org.springblade.modules.iot.vo.UploadDataVO;
import org.springblade.modules.iot.mapper.UploadDataMapper;
import org.springblade.modules.iot.service.IUploadDataService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.dynamic.datasource.annotation.DS;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2019-12-26
 */
@Service
@DS("iot")
//public class UploadDataServiceImpl extends BaseServiceImpl<UploadDataMapper, UploadData> implements IUploadDataService {
public class UploadDataServiceImpl extends ServiceImpl<UploadDataMapper, UploadData> implements IUploadDataService {

	@Resource
	private UploadDataMapper uploadDataMapper;

	@Resource
	private DeviceMapper deviceMapper;

	@Override
	public IPage<UploadDataVO> selectUploadDataPage(IPage<UploadDataVO> page, UploadDataVO uploadData) {
		return page.setRecords(baseMapper.selectUploadDataPage(page, uploadData));
	}

	@Override
	public UploadData getDataByDeviceIdAndKey(String deviceId, String key) {

		Device device = deviceMapper.getDeviceById(deviceId);
		UploadData uploadData = null;
		if(device != null){
			try {
				uploadData = uploadDataMapper.getLatestDataByGatewayAddressAndKey(device.getGatewayAddress(), key);
			}catch (Exception e){
				throw new CannotFindException("未找到相关Kye值数据");
			}
		}else {
			throw new CannotFindException("请先绑定网关");
		}
		return uploadData;
	}

	@Override
	public List<UploadData> getDataListByDeviceIdAndKey(String deviceId, List<String> keyList) {
		List<UploadData> uploadDataList = null;
		Device device = deviceMapper.getDeviceById(deviceId);
//		UploadData uploadData = null;
		if(device != null){
			try {
				for (String key: keyList){
					UploadData data = uploadDataMapper.getLatestDataByGatewayAddressAndKey(device.getGatewayAddress(), key);
					if(data != null){
						uploadDataList.add(data);

					}
				}
			}catch (Exception e){
				throw new CannotFindException("未找到相关Kye值数据");
			}
		}else {
			throw new CannotFindException("请先绑定网关");
		}

		return uploadDataList;
	}

}
