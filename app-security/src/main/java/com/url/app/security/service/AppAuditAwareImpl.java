package com.url.app.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AppAuditAwareImpl implements AuditorAware<Integer> {

	@Autowired
	private AppPrincipalUser appPrincipalUser;

	@Override
	public Optional<Integer> getCurrentAuditor() {
		return Optional.ofNullable(appPrincipalUser.getPrincipalUserUserId());
	}
}