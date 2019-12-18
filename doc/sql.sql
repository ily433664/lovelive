INSERT INTO t_user (id, account, userName, createTime, updateTime, STATUS, deleted) VALUE (1, 'lovelive', 'lovelive', NOW(), NOW(), 1, b'0');


INSERT INTO t_role (id, CODE, NAME, createTime, updateTime, STATUS, deleted, deleteTime) VALUE (100, 'root', '超级管理员', NOW(), NOW(), 1, b'0', null);
INSERT INTO t_role (id, CODE, NAME, createTime, updateTime, STATUS, deleted, deleteTime) VALUE (101, 'manage', '管理员', NOW(), NOW(), 1, b'0', null);
INSERT INTO t_role (id, CODE, NAME, createTime, updateTime, STATUS, deleted, deleteTime) VALUE (102, 'normal', '普通用户', NOW(), NOW(), 1, b'0', null);
INSERT INTO t_role (id, CODE, NAME, createTime, updateTime, STATUS, deleted, deleteTime) VALUE (103, 'guest', '访客', NOW(), NOW(), 1, b'0', null);


INSERT INTO t_user_role (id, user, role, createTime, updateTime, STATUS, deleted) VALUE (1001, 1, 100, NOW(), NOW(), 1, b'0');