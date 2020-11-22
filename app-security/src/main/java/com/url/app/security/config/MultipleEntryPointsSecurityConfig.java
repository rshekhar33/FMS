package com.url.app.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.url.app.security.handler.LoginFailureHandler;
import com.url.app.security.handler.LoginSuccessHandler;
import com.url.app.security.service.AppUserDetailsService;
import com.url.app.security.service.CustomAuthenticationProvider;
import com.url.app.security.service.DbFilterInvocationSecurityMetadataSource;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppUrlView;

/**
 * Spring security configuration for different url patterns.
 * 
 * @author Shekhar Shinde
 */
@Configuration
@EnableWebSecurity
public class MultipleEntryPointsSecurityConfig {

	@Configuration
	@Order(1)
	public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private DbFilterInvocationSecurityMetadataSource dbFilterInvocationSecurityMetadataSource;

		@Autowired
		private CustomAuthenticationProvider customAuthenticationProvider;

		@Autowired
		private LoginSuccessHandler loginSuccessHandlerWeb;

		@Autowired
		private LoginFailureHandler loginFailureHandler;

		@Autowired
		private SessionRegistry sessionRegistry;

		@Autowired
		private AppUserDetailsService appUserDetailsService;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//@formatter:off
			http.antMatcher(AppUrlView.ALL_URL_ANT_PATTERN)
					.authorizeRequests()
					.antMatchers(AppUrlView.PATH_ROOT, AppUrlView.URL_LOGIN).permitAll()
					.anyRequest().authenticated()
				.and().addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
					.formLogin().loginPage(AppUrlView.URL_LOGIN)
					.usernameParameter(AppUrlView.PARAMETER_NAME_USERNAME).passwordParameter(AppUrlView.PARAMETER_NAME_PASSWORD)
					.failureHandler(loginFailureHandler)
					.successHandler(loginSuccessHandlerWeb)
				.and().rememberMe().userDetailsService(appUserDetailsService).rememberMeParameter(AppUrlView.PARAMETER_NAME_REMEMBER_ME)
					.rememberMeCookieName(AppUrlView.COOKIE_NAME_REMEMBER_ME)
				.and().sessionManagement().invalidSessionUrl(AppUrlView.URL_INVALID_SESSION).maximumSessions(1)
					.expiredUrl(AppUrlView.URL_SESSION_EXPIRED).sessionRegistry(sessionRegistry).and()
				.and().logout().logoutUrl(AppUrlView.URL_LOGOUT).logoutSuccessUrl(AppUrlView.URL_LOGIN)
					.invalidateHttpSession(true)
					.deleteCookies(AppUrlView.COOKIE_NAME_JSESSIONID)
				.and().exceptionHandling().accessDeniedPage(AppUrlView.URL_ACCESS_DENIED)
				.and().requiresChannel()
					.anyRequest()
					.requiresSecure()
				.and().csrf()
				.and().headers().cacheControl()
				.and().contentTypeOptions()
				.and().frameOptions()
				.and().httpStrictTransportSecurity()
				.and().xssProtection();
			//@formatter:on
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(customAuthenticationProvider);
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(AppConstant.STATIC_PATH_PATTERN);
		}

		public FilterSecurityInterceptor filterSecurityInterceptor() {
			final FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
			filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
			filterSecurityInterceptor.setSecurityMetadataSource(dbFilterInvocationSecurityMetadataSource);

			return filterSecurityInterceptor;
		}

		public AffirmativeBased accessDecisionManager() {
			final RoleVoter voter = new RoleVoter();
			voter.setRolePrefix(AppConstant.BLANK_STRING);

			final List<AccessDecisionVoter<?>> voters = new ArrayList<>();
			voters.add(voter);

			final AffirmativeBased affirmativeBased = new AffirmativeBased(voters);
			affirmativeBased.afterPropertiesSet();

			return affirmativeBased;
		}
	}
}