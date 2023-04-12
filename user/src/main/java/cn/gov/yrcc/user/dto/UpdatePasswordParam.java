package cn.gov.yrcc.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 修改用户名密码参数
 * Created by yogurt on 2023/3/28.
 */
@Getter
@Setter
public class UpdatePasswordParam {
	@NotEmpty
	@ApiModelProperty(value = "用户名", required = true)
	private Integer id;
	@NotEmpty
	@ApiModelProperty(value = "旧密码", required = true)
	private String oldPassword;
	@NotEmpty
	@ApiModelProperty(value = "新密码", required = true)
	private String newPassword;
}
