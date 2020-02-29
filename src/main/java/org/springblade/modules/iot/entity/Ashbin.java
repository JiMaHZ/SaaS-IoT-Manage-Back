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
 * @since 2020-01-02
 */
@Data
@ApiModel(value = "Ashbin对象", description = "Ashbin对象")
public class Ashbin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */
	@TableId(value = "device_id", type = IdType.UUID)
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    /**
     * 设备信息
     */
    @ApiModelProperty(value = "设备信息")
    private String additionalInfo;
    /**
     * 垃圾桶状态
     */
    @ApiModelProperty(value = "垃圾桶状态")
    private Object ashbinStatus;
    /**
     * 总压
     */
    @ApiModelProperty(value = "总压")
    private String pOrigin;
    /**
     * 分压(数组)
     */
    @ApiModelProperty(value = "分压(数组)")
    private Object pBranch;
	/**
	 * iccid
	 */
	@ApiModelProperty(value = "iccid")
	private String iccid;
	/**
	 * location
	 */
	@ApiModelProperty(value = "location")
	private Object location;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
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
