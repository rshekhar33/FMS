-- -----------------------------------------------------
-- Data for table `common_setting`
-- -----------------------------------------------------
INSERT INTO `common_setting` (`common_setting_id`, `type`, `value`, `order_number`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES (1, 'coursetypecode_counter', '5', 1, 1, now(), 1, now());
INSERT INTO `common_setting` (`common_setting_id`, `type`, `value`, `order_number`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES (2, 'coursecode_counter', '5', 1, 1, now(), 1, now());
INSERT INTO `common_setting` (`common_setting_id`, `type`, `value`, `order_number`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES (3, 'registration_role', '3', 1, 1, now(), 1, now());
INSERT INTO `common_setting` (`common_setting_id`, `type`, `value`, `order_number`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES (4, 'faculty_role', '2', 1, 1, now(), 1, now());

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (1, 'SYSTEM', '$2a$10$9EsDPG9spkDxqLw2rg7u9uQXq79gy40qnyDyLJNC08HBQL3d.eHCS', 'SYSTEM', NULL, 'ADMIN', 'fms_system_admin@gmail.com', '9833073089', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (2, 'shekhar', '$2a$10$Ki42.MJnJyoXDF1eATQCHuIgDcD3KwDMCA1iMIvGkcU4NeMpUJf4K', 'Shekhar', 'B', 'Shinde', 'rshekhar33@gmail.com', '9833073090', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (3, 'sagar', '$2a$10$lCRmhUnFSCAdEgVf.4H6NO13GkZ3YUKX7IdC7a92KAEbFkRBoydEC', 'Sagar', 'S', 'Mule', 'sagar@gmail.com', '9988776601', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (4, 'vishal', '$2a$10$w86zpsukX3wQkKdbNmtq2O90bIqrOD4mnQYbYsRJwWdX44pn6kcfa', 'Vishal', 'V', 'Shejul', 'vishal@gmail.com', '9988776602', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (5, 'akshay', '$2a$10$e7JxPrQ2CTUh8h7S6hx/ceyuDTIJGBDRP0iMznUji4EjSMnWxfZ/G', 'Akshay', 'A', 'Sonawane', 'akshay@gmail.com', '9988776603', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (6, 'ketan', '$2a$10$JXBHynUlKu7SnHaF34mZmONHUVgEYDEHILGooBcKBX3UBWpPQUPH.', 'Ketan', 'K', 'Salvi', 'ketan@gmail.com', '9988776604', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (7, 'amit', '$2a$10$kQ1YeEEQ4DcJeEmVJDlSbO23pTh3ex42oUJ6W9iGgEKOi5wtIe0Dq', 'Amit', 'A', 'Mhatre', 'amit@gmail.com', '9988776605', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (8, 'prashant', '$2a$10$zTvtnBpQ0tqiXQqJReJad.m3jExG/jLx4yceOsivVGL4pJBUq74mG', 'Prashant', 'P', 'Patil', 'prashant@gmail.com', '9988776606', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (9, 'dhawal', '$2a$10$SbAk8Ucv9JP2vgp41kd8rucxk/miNGDBFiwH6rqMdOAdB2cK8v/5O', 'Dhawal', 'D', 'Patil', 'dhawal@gmail.com', '9988776607', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (10, 'santosh', '$2a$10$J4hEcDFJHvDqnPWFvh8FbeN/HPE11OlJE5pF4Vj.kfnw4yoMQBnMm', 'Santosh', 'S', 'Dawange', 'santosh@gmail.com', '9988776608', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (11, 'shashank', '$2a$10$YdpNxTDV/W9JD6ScOUYc2eben3FXAgPc3V142siFPyEQ5TFDqpXbq', 'Shashank', 'S', 'Kadam', 'shashank@gmail.com', '9988776609', 1, now(), 1, now(), 1, NULL, NULL, 0);
INSERT INTO `user` (`user_id`, `user_name`, `password`, `first_name`, `middle_name`, `last_name`, `email_id`, `mobile_no`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `last_successful_login_date`, `last_failed_login_date`, `failed_attempt_cnt`) VALUES (12, 'akash', '$2a$10$hpXlRprC9TTB7CS6K.o3AOQhE3sXLBBud30prE19fzR3jwr/VlAVe', 'Akash', 'A', 'Bagate', 'akash@gmail.com', '9988776610', 1, now(), 1, now(), 1, NULL, NULL, 0);

-- -----------------------------------------------------
-- Data for table `role`
-- -----------------------------------------------------
INSERT INTO `role` (`role_id`, `role_name`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 'Admin', 1, now(), 1, now(), 1);
INSERT INTO `role` (`role_id`, `role_name`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 'Faculty', 1, now(), 1, now(), 1);
INSERT INTO `role` (`role_id`, `role_name`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 'Participant/User', 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `user_role_relation`
-- -----------------------------------------------------
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 1, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 1, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 1, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (4, 2, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (5, 2, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (6, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (7, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (8, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (9, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (10, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (11, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (12, 3, 1, now(), 1, now(), 1);
INSERT INTO `user_role_relation` (`user_id`, `role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (12, 1, 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `privilege`
-- -----------------------------------------------------
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 'Dashboard', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 'User Listing', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 'Add User', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (4, 'Edit User', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (5, 'Activate/Inactivate User', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (6, 'Module Listing', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (7, 'Add Module', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (8, 'Edit Module', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (9, 'Activate/Inactivate Module', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (10, 'Course Type Listing', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (11, 'Add Course Type', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (12, 'Edit Course Type', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (13, 'Activate/Inactivate Course Type', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (14, 'Role Listing', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (15, 'Add Role', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (16, 'Edit Role', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (17, 'Activate/Inactivate Role', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (18, 'Faculty Skillset Listing', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (19, 'Add Faculty Skillset', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (20, 'Edit Faculty Skillset', NULL, 1, now(), 1, now(), 1);
INSERT INTO `privilege` (`privilege_id`, `privilege_name`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (21, 'Activate/Inactivate Faculty Skillset', NULL, 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `role_privilege_relation`
-- -----------------------------------------------------
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 1, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 1, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 1, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 2, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 3, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 4, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 5, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 6, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 7, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 8, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 9, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 10, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 11, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 12, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 13, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 14, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 15, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 16, 1, now(), 1, now(), 1);
INSERT INTO `role_privilege_relation` (`role_id`, `privilege_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 17, 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `action`
-- -----------------------------------------------------
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, '', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'login', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'home', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'loginCheck', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'logout', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'signUp', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'invalidSession', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, NULL, 'sessionExpired', 1, now(), 1, now(), 1, 1);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 1, 'dashboard/dashboard', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 2, 'user/list', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 2, 'user/fetchDetails', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 3, 'user/add', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 3, 'role/fetchActiveDetails', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 3, 'user/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 4, 'user/update', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 4, 'user/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 4, 'role/fetchActiveDetails', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 4, 'user/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 5, 'user/activation', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 6, 'module/list', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 6, 'module/fetchDetails', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 7, 'module/add', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 7, 'module/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 7, 'module/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 8, 'module/update', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 8, 'module/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 8, 'module/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 9, 'module/activation', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 10, 'courseType/list', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 10, 'courseType/fetchDetails', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 11, 'courseType/add', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 11, 'courseType/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 11, 'courseType/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 12, 'courseType/update', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 12, 'courseType/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 12, 'courseType/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 13, 'courseType/activation', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 14, 'role/list', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 14, 'role/fetchDetails', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 15, 'role/add', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 15, 'role/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 15, 'role/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 16, 'role/update', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 16, 'role/fetchData', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 16, 'role/validateSave', 1, now(), 1, now(), 1, 0);
INSERT INTO `action` (`action_id`, `privilege_id`, `action_path`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`, `is_skip`) VALUES (DEFAULT, 17, 'role/activation', 1, now(), 1, now(), 1, 0);

-- -----------------------------------------------------
-- Data for table `module`
-- -----------------------------------------------------
INSERT INTO `module` (`module_id`, `module_name`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 'J2EE', 1, now(), 1, now(), 1);
INSERT INTO `module` (`module_id`, `module_name`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 'Spring', 1, now(), 1, now(), 1);
INSERT INTO `module` (`module_id`, `module_name`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 'Struts', 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `faculty_skillset`
-- -----------------------------------------------------
INSERT INTO `faculty_skillset` (`user_id`, `module_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (4, 1, 1, now(), 1, now(), 1);
INSERT INTO `faculty_skillset` (`user_id`, `module_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (4, 2, 1, now(), 1, now(), 1);
INSERT INTO `faculty_skillset` (`user_id`, `module_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (5, 3, 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `course_type`
-- -----------------------------------------------------
INSERT INTO `course_type` (`course_type_id`, `course_type_code`, `course_type_name`, `no_of_days`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 'CT1', 'Full Time Course', 180, 1, now(), 1, now(), 1);
INSERT INTO `course_type` (`course_type_id`, `course_type_code`, `course_type_name`, `no_of_days`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 'CT2', 'Short Time Course', 90, 1, now(), 1, now(), 1);
INSERT INTO `course_type` (`course_type_id`, `course_type_code`, `course_type_name`, `no_of_days`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 'CT3', 'Crash Course', 15, 1, now(), 1, now(), 1);
INSERT INTO `course_type` (`course_type_id`, `course_type_code`, `course_type_name`, `no_of_days`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (4, 'CT4', 'Rapid Course', 7, 1, now(), 1, now(), 1);

-- -----------------------------------------------------
-- Data for table `course`
-- -----------------------------------------------------
INSERT INTO `course` (`course_id`, `user_id`, `module_id`, `course_type_id`, `course_code`, `start_date`, `end_date`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (1, 4, 1, 1, 'C001', '2016-03-26', '2016-09-26', 1, now(), 1, now(), 1);
INSERT INTO `course` (`course_id`, `user_id`, `module_id`, `course_type_id`, `course_code`, `start_date`, `end_date`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (2, 4, 1, 2, 'C002', '2016-03-26', '2016-06-26', 1, now(), 1, now(), 1);
INSERT INTO `course` (`course_id`, `user_id`, `module_id`, `course_type_id`, `course_code`, `start_date`, `end_date`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (3, 4, 2, 3, 'C003', '2016-03-26', '2016-04-10', 1, now(), 1, now(), 1);
INSERT INTO `course` (`course_id`, `user_id`, `module_id`, `course_type_id`, `course_code`, `start_date`, `end_date`, `created_by`, `created_date`, `modified_by`, `modified_date`, `is_active`) VALUES (4, 5, 3, 4, 'C004', '2016-03-26', '2016-04-02', 1, now(), 1, now(), 1);
