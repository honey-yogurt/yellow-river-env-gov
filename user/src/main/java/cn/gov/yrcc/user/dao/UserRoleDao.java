package cn.gov.yrcc.user.dao;

import cn.gov.yrcc.model.Role;
import cn.gov.yrcc.model.UserRole;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色关系管理自定义Dao
 * Created by yogurt on 2023/4/11.
 */
public interface UserRoleDao {
	/**
	 * 批量插入用户角色关系
	 */
	int insertList(@Param("list") List<UserRole> userRole);

	/**
	 * 获取用于所有角色
	 */
	List<Role> getRoleList(@Param("adminId") Integer userId);

	/**
	 * 获取用户所有可访问资源
	 */
//	List<UmsResource> getResourceList(@Param("adminId") Long adminId);

	/**
	 * 获取资源相关用户ID列表
	 */
//	List<Long> getUserIdList(@Param("resourceId") Long resourceId);
}
