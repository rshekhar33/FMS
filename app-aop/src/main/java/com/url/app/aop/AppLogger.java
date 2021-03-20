package com.url.app.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.url.app.dto.entity.User;
import com.url.app.security.service.AppPrincipalUser;
import com.url.app.utility.AppLogMessage;

/**
 * Logging Aspect.
 * 
 * @author Shekhar Shinde
 */
@Aspect
@Component
public class AppLogger {
	private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired
	private AppPrincipalUser appPrincipalUser;

	@Around("(within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)) "
			+ "&& (execution(* com.url.app.interf.controller.*.*(..)) || execution(* com.url.app.interf.restcontroller.*.*(..)))")
	public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
		final User user = principalUser();

		logger.info(AppLogMessage.USER_ID_USER_NAME_URL_METHOD_TYPE_MSG, user.getUserId(), user.getUserName(), request.getServletPath(), request.getMethod());
		logger.debug(getPreMessage(user, joinPoint));

		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final Object retVal = joinPoint.proceed();

		stopWatch.stop();

		logger.debug(getPostMessage(user, joinPoint, retVal, stopWatch.getTotalTimeMillis()));

		return retVal;
	}

	public User principalUser() {
		User user = appPrincipalUser.getPrincipalUser();
		if (user == null) {
			user = new User();
		}

		return user;
	}

	private static String getPreMessage(final User user, final JoinPoint joinPoint) {
		//@formatter:off
		final StringBuilder builder = new StringBuilder()
				.append(AppLogMessage.USER_ID_TXT_MSG).append(user.getUserId())
				.append(AppLogMessage.USER_NAME_TXT_MSG).append(user.getUserName())
				.append(AppLogMessage.ENTER_WITH_VALUES_TXT_MSG);

		appendMethodParamValues(builder, joinPoint);

		return builder
				.append(AppLogMessage.IN_METHOD_TXT_MSG).append(joinPoint.getSignature().toString())
				.toString();
		//@formatter:on
	}

	private static String getPostMessage(final User user, final JoinPoint joinPoint, final Object retVal, final long millis) {
		//@formatter:off
		return new StringBuilder()
				.append(AppLogMessage.USER_ID_TXT_MSG).append(user.getUserId())
				.append(AppLogMessage.USER_NAME_TXT_MSG).append(user.getUserName())
				.append(AppLogMessage.EXITED_WITH_EXEC_TIME_TXT_MSG).append(millis)
				.append(AppLogMessage.RETURN_VALUE_TXT_MSG).append(retVal)
				.append(AppLogMessage.FROM_METHOD_TXT_MSG).append(joinPoint.getSignature().toString())
				.toString();
		//@formatter:on
	}

	private static void appendMethodParamValues(final StringBuilder builder, final JoinPoint joinPoint) {
		final Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			if (i != 0) {
				builder.append(AppLogMessage.COMMA_TXT_MSG);
			}
			builder.append(args[i]);
		}
	}
}