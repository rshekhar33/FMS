package com.url.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.url.app.dto.entity.User;
import com.url.app.dto.validation.ValidationActivateSequence;
import com.url.app.utility.AppConstant;

@SpringBootTest
class ApplicationWebApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationWebApplicationTests.class);

	@Autowired
	private StringEncryptor stringEncryptor;

	@Autowired
	private LocalValidatorFactoryBean localValidatorFactoryBean;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void jasyptEncDecr() {
		final String plainText = AppConstant.USER_DEFAULT_PASS;
		logger.info("plainText : {}", plainText);

		final String encryptText = stringEncryptor.encrypt(plainText);
		logger.info("encryptText : {}", encryptText);

		final String decryptText = stringEncryptor.decrypt(encryptText);
		logger.info("decryptText : {}", decryptText);

		assertEquals(plainText, decryptText);
	}

	@Test
	void validationTest() {
		final User user = new User();

		final Validator validator = localValidatorFactoryBean.getValidator();
		final Set<ConstraintViolation<User>> violations = validator.validate(user, ValidationActivateSequence.class);
		logger.info("violations : {}", violations);

		assertEquals(2, violations.size());
	}
}