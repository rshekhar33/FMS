package com.url.app.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);

		registry.addResourceHandler(AppConstant.STATIC_PATH_PATTERN).addResourceLocations(AppConstant.STATIC_RESOURCE_LOCATION);
	}
}