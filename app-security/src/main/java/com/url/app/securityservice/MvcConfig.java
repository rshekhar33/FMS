package com.url.app.securityservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.url.app.utility.AppConstant;

/**
 * Spring mvc configuration of application.
 * 
 * @author Shekhar Shinde
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);

		final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(AppConstant.SPRING_VIEW_RESOLVER_PREFIX);
		resolver.setSuffix(AppConstant.SPRING_VIEW_RESOLVER_SUFFIX);
		resolver.setExposedContextBeanNames(AppConstant.SPRING_EXPOSED_BEAN_APP_AUTHORIZATION);
		registry.viewResolver(resolver);
	}
}