package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.Permission;

import java.util.List;

/**
 * 权限 service
 *
 * @author dHe
 */
public interface IPermissionService {

    Permission savePermission(Permission permission);

    List<Permission> findAllNormal();
}
