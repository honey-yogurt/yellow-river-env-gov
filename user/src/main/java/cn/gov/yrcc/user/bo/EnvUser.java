package cn.gov.yrcc.user.bo;

import cn.gov.yrcc.model.User;
import cn.gov.yrcc.model.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SpringSecurity 需要的用户信息封装类
 * Created by yogurt on 2023/3/27.
 */
public class EnvUser implements UserDetails {

	private final User user;

	private List<Role> roles = new ArrayList<>();

	// todo: (yogurt) whether roles is need to pass in the constructor
	public EnvUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		roles.forEach(role->grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameEn())));
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !user.getIsDeleted();
	}
}
