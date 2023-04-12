package cn.gov.yrcc.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户注册参数
 * Created by yogurt on 2023/3/28.
 */
@Getter
@Setter
public class UserParam {
	@NotEmpty
	@ApiModelProperty(value = "用户名", required = true)
	private String username;
	@NotEmpty
	@ApiModelProperty(value = "用户名", required = true)
	private String realName;
	@NotEmpty
	@ApiModelProperty(value = "密码", required = true)
	private String password;
//	@ApiModelProperty(value = "用户头像")
//	private String icon;

	@ApiModelProperty(value = "性别")
	private String gender;

	@ApiModelProperty(value = "部门", required = true)
	private Integer departmentId;

	@ApiModelProperty(value = "密码", required = true)
	private Integer postId;

	@ApiModelProperty(value = "权限", required = true)
	private Integer roleId;

	@ApiModelProperty(value = "用户类型", required = true)
	private Integer userType;

	@Email
	@ApiModelProperty(value = "邮箱")
	private String email;

    // todo: (yogurt) 是否需要校验手机号、或者是座机号
	@ApiModelProperty(value = "电话号码")
	private String phone;

	@ApiModelProperty(value = "地址")
	private String address;

}
