package cn.gov.yrcc.user.service;

import cn.gov.yrcc.model.Role;
import cn.gov.yrcc.model.User;
import cn.gov.yrcc.user.dto.UpdatePasswordParam;
import cn.gov.yrcc.user.dto.UserParam;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理 Service
 * Created by yogurt on 2023/3/27.
 */
public interface EnvUserService {
	/**
	 * 根据用户名获取后台管理员
	 */
//	User getAdminByUsername(String username);

	/**
	 * 注册功能
	 */
	User register(UserParam userParam);

	/**
	 * 登录功能
	 * @param username 用户名
	 * @param password 密码
	 * @return 生成的JWT的token
	 */
	String login(String username,String password);

	/**
	 * 刷新token的功能
	 * @param oldToken 旧的token
	 */
	String refreshToken(String oldToken);

	/**
	 * 根据用户id获取用户
	 */
	User getUser(Integer id);

	/**
	 * 根据用户名或昵称分页查询用户
	 */
	List<User> list(String keyword, Integer pageSize, Integer pageNum);

	/**
	 * 修改指定用户信息
	 */
	int update(Integer id, User user);

	/**
	 * 删除指定用户
	 */
	int delete(Integer id);

	/**
	 * 修改用户角色关系
	 */
	@Transactional
	int updateRole(Integer userId, List<Integer> roleIds);

	/**
	 * 获取用户对应角色
	 */
	List<Role> getRoleList(Integer userId);



	/**
	 * 修改密码
	 */
	int updatePassword(UpdatePasswordParam updatePasswordParam);

	/**
	 * 获取用户信息
	 */
	UserDetails loadUserByUsername(String username);

	/**
	 * 获取缓存服务
	 */
//	UserCacheService getCacheService();
}
