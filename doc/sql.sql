INSERT INTO t_user (id, account, username, create_time, update_time, status, deleted, flag, modify_password) VALUE ('1', 'lovelive', 'lovelive', now(), now(), 1, b'0', 0, b'0');


INSERT INTO t_role (id, code, NAME, create_time, update_time, status, deleted, flag) VALUE ('100', 'root', '超级管理员', now(), now(), 1, b'0', 0);
INSERT INTO t_role (id, code, NAME, create_time, update_time, status, deleted, flag) VALUE ('101', 'manage', '管理员', now(), now(), 1, b'0', 0);
INSERT INTO t_role (id, code, NAME, create_time, update_time, status, deleted, flag) VALUE ('102', 'normal', '普通用户', now(), now(), 1, b'0', 0);
INSERT INTO t_role (id, code, NAME, create_time, update_time, status, deleted, flag) VALUE ('103', 'guest', '访客', now(), now(), 1, b'0', 0);


INSERT INTO t_user_role (id, user_id, role_id, create_time, update_time, status, deleted, flag) VALUE ('1001', '1', '100', now(), now(), 1, b'0', 0);