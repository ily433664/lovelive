package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.Role;

import java.util.List;

/**
 * 角色 service
 *
 * @author dHe
 */
public interface IRoleService {

    Role saveRole(Role role);

    List<Role> findAllNormal();
}
