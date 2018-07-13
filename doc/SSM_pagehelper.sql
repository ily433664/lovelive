DROP TABLE IF EXISTS `user_t`;
CREATE TABLE user_t (
	u_id BIGINT(15) NOT NULL AUTO_INCREMENT,
	account VARCHAR(255) NOT NULL COMMENT '账号',
	PASSWORD VARCHAR(255) NOT NULL COMMENT '密码',
	user_name VARCHAR(40) NOT NULL COMMENT '名称',
	STATUS INT(3) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	last_login_time DATETIME COMMENT '最后一次登录时间',
	PRIMARY KEY (u_id)
) ENGINE=INNODB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;
INSERT INTO user_t (account, PASSWORD, user_name, STATUS) VALUES ('lovelive', '4be34ed0e68957bebdcdb37b372420b60605f256f04ac2e383ec15ad', 'lovelive', 1);
INSERT INTO user_t (account, PASSWORD, user_name, STATUS) VALUES ('admin', '70ad3e97cbf4b778a754d4234ae4e22f2cb674a5aa9eff396136c858', '管理员', 1);
INSERT INTO user_t (account, PASSWORD, user_name, STATUS) VALUES ('test1', '20bc9ed3ef12010d9fffbe64067d718a9559e817604c34b14ef9c659', '测试1', 1);
INSERT INTO user_t (account, PASSWORD, user_name, STATUS) VALUES ('test2', '582c1cc3b68085274e8ce3ce117d0517f5bbbf993c5bbf9e2af771bc', '测试2', 1);
INSERT INTO user_t (account, PASSWORD, user_name, STATUS) VALUES ('test3', '3acd77b460829f646fc3608ae4142ed5fae34e083a774cf8abce6b17', '测试3', 1);
INSERT INTO user_t (u_id, account, PASSWORD, user_name, STATUS) VALUES (1,'test', 'd8d6d48961e684937665ab1dee0dba987efdfefd29ef5cbf6559d5ec', '测试', 1);
INSERT INTO user_t (u_id, account, PASSWORD, user_name, STATUS) VALUES (2,'mis', '4d0d2eba195114b8de37bb7e1efe409273b2f563cb520f62a32602c1', 'mis测试', 1);



DROP TABLE IF EXISTS `role_t`;
CREATE TABLE role_t (
	r_id BIGINT(15) NOT NULL AUTO_INCREMENT,
	r_name VARCHAR(255) NOT NULL COMMENT '名称',
	r_code VARCHAR(255) NOT NULL COMMENT '代码',
	STATUS INT(3) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (r_id)
) ENGINE=INNODB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
INSERT INTO role_t (r_id, r_name, r_code, STATUS) VALUE (100, '超级管理员', 'root', 1);
INSERT INTO role_t (r_id, r_name, r_code, STATUS) VALUE (101, '管理员', 'manage', 1);
INSERT INTO role_t (r_id, r_name, r_code, STATUS) VALUE (102, '普通用户', 'normal', 1);
INSERT INTO role_t (r_id, r_name, r_code, STATUS) VALUE (103, '访客', 'guest', 1);



DROP TABLE IF EXISTS `user_role_t`;
CREATE TABLE user_role_t (
	ur_id BIGINT(15) NOT NULL AUTO_INCREMENT,
	u_id BIGINT(15) NOT NULL COMMENT '用户',
	r_id BIGINT(15) NOT NULL COMMENT '角色',
	STATUS INT(3) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (ur_id),
	CONSTRAINT fk_ur_user_t FOREIGN KEY (u_id) REFERENCES user_t(u_id),
	CONSTRAINT fk_ur_role_t FOREIGN KEY (r_id) REFERENCES role_t(r_id)
) ENGINE=INNODB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;
INSERT INTO user_role_t (u_id, r_id, STATUS) VALUE (100000000, 100, 1);
INSERT INTO user_role_t (u_id, r_id, STATUS) VALUE (100000001, 100, 1);
INSERT INTO user_role_t (u_id, r_id, STATUS) VALUE (100000002, 101, 1);
INSERT INTO user_role_t (u_id, r_id, STATUS) VALUE (100000003, 102, 1);
INSERT INTO user_role_t (u_id, r_id, STATUS) VALUE (100000004, 103, 1);



DROP TABLE IF EXISTS `perm_t`;
CREATE TABLE perm_t (
	p_id VARCHAR(255) NOT NULL,
	p_name VARCHAR(255) NOT NULL COMMENT '名称',
	STATUS INT(3) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (p_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO perm_t (p_id, p_name, STATUS) VALUE ('test', '测试', 1);
INSERT INTO perm_t (p_id, p_name, STATUS) VALUE ('test:test1', '测试1', 1);
INSERT INTO perm_t (p_id, p_name, STATUS) VALUE ('test:test2', '测试2', 1);
INSERT INTO perm_t (p_id, p_name, STATUS) VALUE ('test:test3', '测试3', 1);



DROP TABLE IF EXISTS `role_perm_t`;
CREATE TABLE role_perm_t (
	rp_id BIGINT(15) NOT NULL AUTO_INCREMENT,
	r_id BIGINT(15) NOT NULL COMMENT '角色',
	p_id VARCHAR(255) NOT NULL COMMENT '权限',
	STATUS INT(3) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (rp_id),
	CONSTRAINT fk_rp_role_t FOREIGN KEY (r_id) REFERENCES role_t(r_id),
	CONSTRAINT fk_rp_perm__t FOREIGN KEY (p_id) REFERENCES perm_t(p_id)
) ENGINE=INNODB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;
INSERT INTO role_perm_t (r_id, p_id, STATUS) VALUE (100, 'test', 1);
INSERT INTO role_perm_t (r_id, p_id, STATUS) VALUE (100, 'test:test1', 1);
INSERT INTO role_perm_t (r_id, p_id, STATUS) VALUE (100, 'test:test2', 1);
INSERT INTO role_perm_t (r_id, p_id, STATUS) VALUE (100, 'test:test3', 1);



DROP TABLE IF EXISTS `operation_log_t`;
CREATE TABLE operation_log_t (
	ol_id BIGINT(20) NOT NULL AUTO_INCREMENT,
	oper_account VARCHAR(255) COMMENT '操作人账号',
	oper_name VARCHAR(255) COMMENT '操作人名称',
	oper_role VARCHAR(255) COMMENT '操作人角色',
	oper_IP VARCHAR(255) COMMENT 'IP地址',
	oper_type VARCHAR(255) COMMENT '操作类型',
	oper_function VARCHAR(255) COMMENT '操作功能',
	oper_method VARCHAR(255) NOT NULL COMMENT '操作方法',
	oper_URI VARCHAR(255) NOT NULL COMMENT '请求URI',
	oper_parameter VARCHAR(255) COMMENT '请求参数',
	response_time BIGINT NOT NULL COMMENT '响应时间',
	if_success TINYINT(1) COMMENT '操作是否成功',
	result_content VARCHAR(4000) COMMENT '本次操作的结果',
	STATUS INT(3) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (ol_id)
) ENGINE=INNODB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `login_log_t`;
CREATE TABLE login_log_t (
	ll_id BIGINT(20) NOT NULL AUTO_INCREMENT,
	u_id INT(15) NOT NULL COMMENT '用户ID',
	u_account VARCHAR(255) NOT NULL COMMENT '用户账号',
	login_IP VARCHAR(255) COMMENT 'IP地址',
	if_success TINYINT(1) COMMENT '是否登录成功',
	result VARCHAR(255) COMMENT '结果',
	STATUS INT(1) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (ll_id)
) ENGINE=INNODB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `test_page`;
CREATE TABLE test_page (
	id BIGINT(10) NOT NULL AUTO_INCREMENT,
	CODE VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	STATUS INT(1) NOT NULL COMMENT '记录状态',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode1', 'testName1', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode2', 'testName2', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode3', 'testName3', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode4', 'testName4', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode5', 'testName5', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode6', 'testName6', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode7', 'testName7', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode8', 'testName8', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode9', 'testName9', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode10', 'testName10', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode11', 'testName11', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode12', 'testName12', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode13', 'testName13', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode14', 'testName14', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode15', 'testName15', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode16', 'testName16', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode17', 'testName17', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode18', 'testName18', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode19', 'testName19', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode20', 'testName20', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode21', 'testName21', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode22', 'testName22', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode23', 'testName23', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode24', 'testName24', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode25', 'testName25', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode26', 'testName26', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode27', 'testName27', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode28', 'testName28', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode29', 'testName29', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode30', 'testName30', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode31', 'testName31', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode32', 'testName32', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode33', 'testName33', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode34', 'testName34', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode35', 'testName35', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode36', 'testName36', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode37', 'testName37', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode38', 'testName38', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode39', 'testName39', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode40', 'testName40', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode41', 'testName41', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode42', 'testName42', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode43', 'testName43', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode44', 'testName44', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode45', 'testName45', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode46', 'testName46', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode47', 'testName47', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode48', 'testName48', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode49', 'testName49', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode50', 'testName50', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode51', 'testName51', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode52', 'testName52', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode53', 'testName53', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode54', 'testName54', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode55', 'testName55', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode56', 'testName56', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode57', 'testName57', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode58', 'testName58', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode59', 'testName59', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode60', 'testName60', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode61', 'testName61', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode62', 'testName62', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode63', 'testName63', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode64', 'testName64', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode65', 'testName65', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode66', 'testName66', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode67', 'testName67', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode68', 'testName68', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode69', 'testName69', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode70', 'testName70', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode71', 'testName71', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode72', 'testName72', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode73', 'testName73', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode74', 'testName74', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode75', 'testName75', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode76', 'testName76', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode77', 'testName77', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode78', 'testName78', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode79', 'testName79', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode80', 'testName80', 1);

INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode81', 'testName81', 1);
INSERT INTO test_page (CODE, NAME, STATUS) VALUE ('testCode82', 'testName82', 1);


