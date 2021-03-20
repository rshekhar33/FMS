package com.url.app.pojo;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.url.app.dto.entity.Role;
import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserRoleRelation;
import com.url.app.utility.AppConstant;

/**
 * @author Shekhar Shinde
 */
public class LoggedUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	private User user;

	public LoggedUser() {
		super();
	}

	public LoggedUser(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getUser().getUserRoleRelations().stream().map(UserRoleRelation::getRole).map(Role::getRoleId).map(String::valueOf).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return getUser().getPassword();
	}

	@Override
	public String getUsername() {
		return getUser().getUserName();
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
		return AppConstant.ACTIVE.equals(getUser().getIsActive());
	}

	@Override
	public int hashCode() {
		return Objects.hash(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LoggedUser)) {
			return false;
		}
		LoggedUser other = (LoggedUser) obj;

		return Objects.equals(user, other.user);
	}
}