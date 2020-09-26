package com.Tang_tzb.service;

import com.Tang_tzb.domain.Permission;

import java.util.List;

public interface IPermissionService {

    void save(Permission permission) throws Exception;

    List<Permission> findAll() throws Exception;

    Permission findById(String id) throws Exception;

    void deleteById(String id) throws Exception;
}
