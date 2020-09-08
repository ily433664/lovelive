package com.lovelive.sys.service;

import com.lovelive.sys.entity.Permission;

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
