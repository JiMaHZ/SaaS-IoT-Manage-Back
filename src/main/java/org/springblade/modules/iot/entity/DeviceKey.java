package org.springblade.modules.iot.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class DeviceKey {

	@NotBlank(message = "设备Id不能为空")
	private String deviceId;

	private String deviceKey;

	private String keyName;

	private String value;

	private String min;

	private String max;

	private String dataStep;

	private String dataOriginWidth;

	private String unit;

	private String dataType;

	private String rule;

	/**
	 * 修改时间
	 */
	private LocalDateTime modifyTime;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
}
