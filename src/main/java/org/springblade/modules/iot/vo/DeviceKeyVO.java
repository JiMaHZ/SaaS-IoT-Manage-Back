package org.springblade.modules.iot.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.entity.DeviceKey;


@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceKeyVO extends DeviceKey {
	private static final long serialVersionUID = 1L;

}
