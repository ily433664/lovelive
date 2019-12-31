package com.lovelive.sys.service;

import com.lovelive.sys.entity.Role;

import java.util.List;

/**
 * 角色 service
 *
 * @author dHe
 * @date 2019-4-29
 */
public interface IRoleService {

    Role saveRole(Role role);

    List<Role> findAllNormal();
}
