-- -----------------------------------------------------
-- Table `common_setting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `common_setting` (
  `common_setting_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  `value` VARCHAR(100) NULL,
  `order_number` INT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`common_setting_id`));


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `middle_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `email_id` VARCHAR(100) NOT NULL,
  `mobile_no` VARCHAR(20) NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  `last_successful_login_date` DATETIME NULL,
  `last_failed_login_date` DATETIME NULL,
  `failed_attempt_cnt` INT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`));

CREATE UNIQUE INDEX `email_id_UNIQUE` ON `user` (`email_id` ASC);

CREATE UNIQUE INDEX `user_name_UNIQUE` ON `user` (`user_name` ASC);


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`role_id`));

CREATE UNIQUE INDEX `role_name_UNIQUE` ON `role` (`role_name` ASC);


-- -----------------------------------------------------
-- Table `user_role_relation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role_relation` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`user_id`, `role_id`),
  CONSTRAINT `fk_user_role_relation_0`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_relation_1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_user_role_relation_0` ON `user_role_relation` (`user_id` ASC);

CREATE INDEX `idx_user_role_relation_1` ON `user_role_relation` (`role_id` ASC);

CREATE INDEX `idx_user_role_relation_2` ON `user_role_relation` (`user_id` ASC, `role_id` ASC);


-- -----------------------------------------------------
-- Table `privilege`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `privilege` (
  `privilege_id` INT NOT NULL AUTO_INCREMENT,
  `privilege_name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`privilege_id`));

CREATE UNIQUE INDEX `privilege_name_UNIQUE` ON `privilege` (`privilege_name` ASC);


-- -----------------------------------------------------
-- Table `role_privilege_relation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role_privilege_relation` (
  `role_id` INT NOT NULL,
  `privilege_id` INT NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`role_id`, `privilege_id`),
  CONSTRAINT `fk_role_privilege_relation_0`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_privilege_relation_1`
    FOREIGN KEY (`privilege_id`)
    REFERENCES `privilege` (`privilege_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_role_privilege_relation_0` ON `role_privilege_relation` (`role_id` ASC);

CREATE INDEX `idx_role_privilege_relation_1` ON `role_privilege_relation` (`privilege_id` ASC);

CREATE INDEX `idx_role_privilege_relation_2` ON `role_privilege_relation` (`role_id` ASC, `privilege_id` ASC);


-- -----------------------------------------------------
-- Table `action`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `action` (
  `action_id` INT NOT NULL AUTO_INCREMENT,
  `privilege_id` INT NULL,
  `action_path` VARCHAR(100) NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  `is_skip` INT NOT NULL DEFAULT 0 COMMENT '0 - Authentication required\n1 - Authentication to be skipped',
  PRIMARY KEY (`action_id`),
  CONSTRAINT `fk_action_0`
    FOREIGN KEY (`privilege_id`)
    REFERENCES `privilege` (`privilege_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_action_0` ON `action` (`privilege_id` ASC);


-- -----------------------------------------------------
-- Table `module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module` (
  `module_id` INT NOT NULL AUTO_INCREMENT,
  `module_name` VARCHAR(100) NOT NULL COMMENT 'Display name of module',
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`module_id`));

CREATE UNIQUE INDEX `module_name_UNIQUE` ON `module` (`module_name` ASC);


-- -----------------------------------------------------
-- Table `user_skillset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_skillset` (
  `user_id` INT NOT NULL,
  `module_id` INT NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`user_id`, `module_id`),
  CONSTRAINT `fk_user_skillset_0`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_skillset_1`
    FOREIGN KEY (`module_id`)
    REFERENCES `module` (`module_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_user_skillset_0` ON `user_skillset` (`user_id` ASC);

CREATE INDEX `idx_user_skillset_1` ON `user_skillset` (`module_id` ASC);

CREATE INDEX `idx_user_skillset_2` ON `user_skillset` (`user_id` ASC, `module_id` ASC);


-- -----------------------------------------------------
-- Table `course_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_type` (
  `course_type_id` INT NOT NULL AUTO_INCREMENT,
  `course_type_code` VARCHAR(50) NOT NULL,
  `course_type_name` VARCHAR(500) NOT NULL,
  `no_of_days` INT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`course_type_id`));

CREATE UNIQUE INDEX `course_type_code_UNIQUE` ON `course_type` (`course_type_code` ASC);


-- -----------------------------------------------------
-- Table `course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `module_id` INT NOT NULL,
  `course_type_id` INT NOT NULL,
  `course_code` VARCHAR(50) NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`course_id`),
  CONSTRAINT `fk_course_0`
    FOREIGN KEY (`user_id` , `module_id`)
    REFERENCES `user_skillset` (`user_id` , `module_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_1`
    FOREIGN KEY (`course_type_id`)
    REFERENCES `course_type` (`course_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_course_0` ON `course` (`user_id` ASC, `module_id` ASC);

CREATE INDEX `idx_course_1` ON `course` (`course_type_id` ASC);

CREATE UNIQUE INDEX `course_code_UNIQUE` ON `course` (`course_code` ASC);


-- -----------------------------------------------------
-- Table `feedback_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `feedback_question` (
  `feedback_question_id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `question` VARCHAR(500) NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`feedback_question_id`),
  CONSTRAINT `fk_feedback_question_0`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_feedback_question_0` ON `feedback_question` (`course_id` ASC);


-- -----------------------------------------------------
-- Table `feedback_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `feedback_answer` (
  `feedback_answer_id` INT NOT NULL AUTO_INCREMENT,
  `feedback_question_id` INT NOT NULL,
  `answer` VARCHAR(500) NOT NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NOT NULL,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` INT NOT NULL DEFAULT 0 COMMENT '0 - Inactive\n1 - Active',
  PRIMARY KEY (`feedback_answer_id`),
  CONSTRAINT `fk_feedback_answer_0`
    FOREIGN KEY (`feedback_question_id`)
    REFERENCES `feedback_question` (`feedback_question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `idx_feedback_answer_0` ON `feedback_answer` (`feedback_question_id` ASC);
