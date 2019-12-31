package com.lovelive.sys.service;

import com.lovelive.sys.entity.Permission;

import java.util.List;

/**
 * 权限 service
 *
 * @author dHe
 * @date 2019-4-29
 */
public interface IPermissionService {

    Permission savePermission(Permission permission);

    List<Permission> findAllNormal();
}
