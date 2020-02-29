package org.springblade.modules.iot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 硬件端自动载入--->用于绑定device_id
 */
@Data
public class Device {
	@TableId(value = "id", type = IdType.INPUT)
	private String gatewayAddress;

	private String collectorAddress;

	private String iccid;

	/**
	 * 纬度
	 */
	private String lat;

	/**
	 * 经度
	 */
	private String lng;

	private String deviceId;

	private String deviceName;

	private String deviceStatus;

	private String deviceType;
	/**
	 * 修改时间
	 */
	private LocalDateTime modifyTime;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

}
