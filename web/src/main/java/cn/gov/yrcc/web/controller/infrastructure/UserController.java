package cn.gov.yrcc.web.controller.infrastructure;

import cn.gov.yrcc.model.User;
import cn.gov.yrcc.shared.api.CommonResult;
import cn.gov.yrcc.user.dto.UserParam;
import cn.gov.yrcc.user.service.EnvUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后台用户管理Controller
 * Created by yogurt on 2023/4/11.
 */
@RestController
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/user")
public class UserController {
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	@Resource
	private EnvUserService envUserService;
//	@Resource
//	private RoleService roleService;

	@ApiOperation(value = "用户注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public CommonResult<User> register(@Validated @RequestBody UserParam userParam) {
		User umsAdmin = envUserService.register(userParam);
		if (umsAdmin == null) {
			return CommonResult.failed();
		}
		return CommonResult.success(umsAdmin);
	}
}
