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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.gson.JsonObject;
import org.springblade.core.mp.support.Condition;
import org.springblade.modules.iot.entity.Ashbin;
import org.springblade.modules.iot.entity.Device;
import org.springblade.modules.iot.entity.UploadData;
import org.springblade.modules.iot.exception.CannotFindException;
import org.springblade.modules.iot.mapper.DeviceMapper;
import org.springblade.modules.iot.mapper.UploadDataMapper;
import org.springblade.modules.iot.vo.AshbinVO;
import org.springblade.modules.iot.mapper.AshbinMapper;
import org.springblade.modules.iot.service.IAshbinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springblade.modules.iot.util.gpsConvertUtil.getDoubleNum;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2020-01-02
 */
@Service
@DS("iot")
public class AshbinServiceImpl extends ServiceImpl<AshbinMapper, Ashbin> implements IAshbinService {

	@Resource
	private DeviceMapper deviceMapper;

	@Resource
	private UploadDataMapper uploadDataMapper;

	@Override
	public IPage<AshbinVO> selectAshbinPage(IPage<AshbinVO> page, AshbinVO ashbin) {
		return page.setRecords(baseMapper.selectAshbinPage(page, ashbin));
	}

	@Override
	public Ashbin getAshbinStatus(String deviceId) {
		Ashbin ashbin =new Ashbin();
//		Device device = new Device();
//		device.setDeviceId(deviceId);
//		deviceMapper.selectOne(Condition.getQueryWrapper(device))
		Device device = deviceMapper.getDeviceById(deviceId);
		if(device != null){
			ashbin.setDeviceId(device.getDeviceId());
			ashbin.setDeviceName(device.getDeviceName());
			ashbin.setAshbinStatus(device.getDeviceStatus());
			System.out.println(device);

			//0940 iccid号+经纬度
			UploadData uploadData1 = uploadDataMapper.getLatestDataByGatewayAddressAndKey(device.getGatewayAddress(), "0940");

			if(uploadData1 != null){
				System.out.println(uploadData1);
				String deviceValueStr = uploadData1.getDeviceValue();

				try {
					JSONObject deviceValueObject = JSON.parseObject(deviceValueStr);
					System.out.println("JSONOBJECT");
					System.out.println(deviceValueObject);

					String ICCID = deviceValueObject.getString("iccid");
					Map<String,String> location = new HashMap<>();
					String latDouble = null;
					String lngDouble = null;
					if(deviceValueObject.getString("lat") != null){
						latDouble =  getDoubleNum(deviceValueObject.getString("lat"));
						lngDouble =  getDoubleNum(deviceValueObject.getString("lng"));
					}

					location.put("lat",latDouble);
					location.put("latIcon",deviceValueObject.getString("latIcon"));
					location.put("lng",lngDouble);
					location.put("lngIcon",deviceValueObject.getString("lngIcon"));

					ashbin.setIccid(ICCID);
					ashbin.setLocation(location);
					ashbin.setCreateTime(device.getCreateTime());
					ashbin.setLastUpdateTime(uploadData1.getUploadTime());

					System.out.println(ashbin.toString());
				}catch (Exception e){
					log.error("解析异常");
				}
			}
			//0B00 垃圾桶状态
			UploadData uploadData2 = uploadDataMapper.getLatestDataByGatewayAddressAndKey(device.getGatewayAddress(), "0B00");

			if(uploadData2 != null){
				String deviceValueStr = uploadData2.getDeviceValue();
				String[] valueArr = deviceValueStr.substring(1, deviceValueStr.length() - 1).split(", ");
				JSONObject deviceStatusObject = new JSONObject();
				if(valueArr.length>=4){
					deviceStatusObject.put("1",valueArr[0]);
					deviceStatusObject.put("2",valueArr[1]);
					deviceStatusObject.put("3",valueArr[2]);
					deviceStatusObject.put("4",valueArr[3]);
					deviceStatusObject.put("uploadTime",uploadData2.getUploadTime());
					ashbin.setAshbinStatus(deviceStatusObject);
				}
			}

			//0900 总压及分压+三个备用
			UploadData uploadData3 = uploadDataMapper.getLatestDataByGatewayAddressAndKey(device.getGatewayAddress(), "0900");
			if(uploadData3 != null){
				String deviceValueStr = uploadData3.getDeviceValue();
				String[] valueArr = deviceValueStr.substring(1, deviceValueStr.length() - 1).split(", ");
				JSONObject deviceStatusObject = new JSONObject();
				if(valueArr.length>=8){
//					deviceStatusObject.put("originP",valueArr[0]);
					deviceStatusObject.put("branchP01",valueArr[1]);
					deviceStatusObject.put("branchP02",valueArr[2]);
					deviceStatusObject.put("branchP03",valueArr[3]);
					deviceStatusObject.put("branchP04",valueArr[4]);
					deviceStatusObject.put("backup01",valueArr[5]);
					deviceStatusObject.put("backup02",valueArr[6]);
					deviceStatusObject.put("backup03",valueArr[7]);
					deviceStatusObject.put("uploadTime",uploadData3.getUploadTime());
					ashbin.setPOrigin(valueArr[0]);
					ashbin.setPBranch(deviceStatusObject);
				}
			}
		}else {
			throw new CannotFindException("请先绑定网关地址！");
		}
		return ashbin;
	}

	/**
	 * TODO 查询压力及分压
	 * @param deviceId
	 * @return
	 */
	@Override
	public Ashbin getAshbinPressData(String deviceId) {

//		UploadData uploadData2 = uploadDataMapper.getLatestDataByGatewayAddressAndKey(device.getGatewayAddress(), "0B00");

		return null;
	}

}
