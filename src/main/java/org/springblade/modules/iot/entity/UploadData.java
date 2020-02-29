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

import com.baomidou.mybatisplus.annotation.IdType;
import org.springblade.core.mp.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Blade
 * @since 2019-12-26
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UploadData对象", description = "UploadData对象")
//public class UploadData extends BaseEntity {
public class UploadData implements Serializable {

    private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
    /**
     * 网关地址
     */
    @ApiModelProperty(value = "网关地址")
    private String gatewayAddress;
    /**
     * 采控器地址
     */
    @ApiModelProperty(value = "采控器地址")
    private String collectorAddress;
    /**
     * 命令
     */
    @ApiModelProperty(value = "命令")
    private String command;
    /**
     * 设备键
     */
    @ApiModelProperty(value = "设备键")
    private String deviceKey;
    /**
     * 设备键
     */
    @ApiModelProperty(value = "设备键")
    private String deviceValue;
    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间")
    private LocalDateTime uploadTime;
    /**
     * 远程连接地址
     */
    @ApiModelProperty(value = "远程连接地址")
    private String remoteAddress;


}
