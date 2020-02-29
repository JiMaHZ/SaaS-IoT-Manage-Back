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
package org.springblade.modules.iot.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Blade
 * @since 2019-12-27
 */
@Data
@ApiModel(value = "DeviceStatus对象", description = "DeviceStatus对象")
public class DeviceStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */
	@TableId(value = "device_id", type = IdType.UUID)
    @ApiModelProperty(value = "设备id")
    private String deviceId;
	/**
	 * 设备名
	 */
	@ApiModelProperty(value = "设备名")
	private String deviceName;
    /**
     * 设备状态
     */
    @ApiModelProperty(value = "设备状态")
    private Boolean deviceStatus;
	/**
	 * 设备类型
	 */
	@ApiModelProperty(value = "设备类型")
	private String deviceType;
    /**
     * 远程连接地址
     */
    @ApiModelProperty(value = "远程连接地址")
    private String remoteAddress;
    /**
     * 最后上传时间
     */
    @ApiModelProperty(value = "最后上传时间")
    private LocalDateTime lastUpdateTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
