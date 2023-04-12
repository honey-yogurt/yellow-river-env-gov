package cn.gov.yrcc.user.service.impl;

import cn.gov.yrcc.infrastructure.exception.Asserts;
import cn.gov.yrcc.infrastructure.security.util.JwtTokenUtil;
import cn.gov.yrcc.mapper.UserMapper;
import cn.gov.yrcc.mapper.UserRoleMapper;
import cn.gov.yrcc.model.*;
import cn.gov.yrcc.user.dao.UserRoleDao;
import cn.gov.yrcc.user.dto.UpdatePasswordParam;
import cn.gov.yrcc.user.dto.UserParam;
import cn.gov.yrcc.user.service.EnvUserService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yogurt on 2023/3/28.
 */
@Service
public class EnvUserServiceImpl implements EnvUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EnvUserServiceImpl.class);
	@Resource
	private JwtTokenUtil jwtTokenUtil;
	@Resource
	private PasswordEncoder passwordEncoder;
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;
	@Resource
	private UserRoleDao userRoleDao;
	@Override
	public User register(UserParam userParam) {
		User user = new User();
		BeanUtils.copyProperties(userParam,user);
		user.setIsDeleted(false);
		// todo: 查询是否有相同真实姓名的用户，理论上允许相同真实姓名的用户，如果后续需要加入身份证号码，则需要在这里判断身份证号
//		UserExample example = new UserExample();
//		example.createCriteria().andRealNameEqualTo(user.getRealName());
//		List<User> userList = userMapper.selectByExample(example);
//		if (userList.size() > 0) {
//			return null;
//		}
		// 将密码进行加密
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		userMapper.insert(user);
		return user;
	}

	@Override
	public String login(String username, String password) {
		String token = null;
		//密码需要客户端加密后传递
		try {
			UserDetails userDetails = loadUserByUsername(username);
			if(!passwordEncoder.matches(password,userDetails.getPassword())){
				Asserts.fail("密码不正确");
			}
			if(!userDetails.isEnabled()){
				Asserts.fail("帐号已被禁用");
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
			// todo: 日志记录表后续加入
//			insertLoginLog(username);
		} catch (AuthenticationException e) {
			LOGGER.warn("登录异常:{}", e.getMessage());
		}
		return token;
	}

	@Override
	public String refreshToken(String oldToken) {
		return jwtTokenUtil.refreshHeadToken(oldToken);
	}

	@Override
	public User getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> list(String keyword, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (!StrUtil.isEmpty(keyword)) {
			criteria.andUsernameLike("%" + keyword + "%");
			example.or(example.createCriteria().andRealNameLike("%" + keyword + "%"));
		}
		return userMapper.selectByExample(example);
	}

	@Override
	public int update(Integer id, User user) {
		user.setId(id);
		User rawUser = userMapper.selectByPrimaryKey(id);
		if(rawUser.getPassword().equals(user.getPassword())){
			//与原加密密码相同的不需要修改
			user.setPassword(null);
		}else{
			//与原加密密码不同的需要加密修改
			if(StrUtil.isEmpty(user.getPassword())){
				user.setPassword(null);
			}else{
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		}
		int count = userMapper.updateByPrimaryKeySelective(user);
		// todo : 缓存服务后续加入
//		getCacheService().delAdmin(id);
		return count;
	}

	@Override
	public int delete(Integer id) {
//		getCacheService().delAdmin(id);
		int count = userMapper.deleteByPrimaryKey(id);
//		getCacheService().delResourceList(id);
		return count;
	}

	@Override
	public int updateRole(Integer userId, List<Integer> roleIds) {
		int count = roleIds == null ? 0 : roleIds.size();
		//先删除原来的关系
		UserRoleExample userRoleExample = new UserRoleExample();
		userRoleExample.createCriteria().andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(userRoleExample);
		//建立新关系
		if (!CollectionUtils.isEmpty(roleIds)) {
			List<UserRole> list = new ArrayList<>();
			for (Integer roleId : roleIds) {
				UserRole roleRelation = new UserRole();
				roleRelation.setUserId(userId);
				roleRelation.setRoleId(roleId);
				list.add(roleRelation);
			}
			userRoleDao.insertList(list);
		}
//		getCacheService().delResourceList(adminId);
		return count;
	}

	@Override
	public List<Role> getRoleList(Integer userId) {
		return userRoleDao.getRoleList(userId);
	}

	@Override
	public int updatePassword(UpdatePasswordParam param) {
		if(param.getId() == null
			||StrUtil.isEmpty(param.getOldPassword())
			||StrUtil.isEmpty(param.getNewPassword())){
			return -1;
		}
		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(param.getId());
		List<User> userList = userMapper.selectByExample(example);
		if(CollUtil.isEmpty(userList)){
			return -2;
		}
		User user = userList.get(0);
		if(!passwordEncoder.matches(param.getOldPassword(),user.getPassword())){
			return -3;
		}
		user.setPassword(passwordEncoder.encode(param.getNewPassword()));
		userMapper.updateByPrimaryKey(user);
//		getCacheService().delAdmin(umsAdmin.getId());
		return 1;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return null;
	}
}
