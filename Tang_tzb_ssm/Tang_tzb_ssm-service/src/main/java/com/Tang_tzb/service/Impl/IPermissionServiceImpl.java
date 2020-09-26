package com.Tang_tzb.service.Impl;

import com.Tang_tzb.dao.IPermissionDao;
import com.Tang_tzb.domain.Permission;
import com.Tang_tzb.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    IPermissionDao iPermissionDao;
    @Override
    public void save(Permission permission) throws Exception {
        iPermissionDao.save(permission);
    }

    @Override
    public List<Permission> findAll() throws Exception {
        return iPermissionDao.findAll();
    }

    @Override
    public Permission findById(String id) throws Exception {
        return iPermissionDao.findById(id);
    }

    @Override
    public void deleteById(String id) throws Exception {
        iPermissionDao.deleteFromRole_Permission(id);
        iPermissionDao.deleteById(id);
    }
}
