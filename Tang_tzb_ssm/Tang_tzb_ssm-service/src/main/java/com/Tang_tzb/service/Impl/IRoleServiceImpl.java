package com.Tang_tzb.service.Impl;

import com.Tang_tzb.dao.IRoleDao;
import com.Tang_tzb.domain.Permission;
import com.Tang_tzb.domain.Role;
import com.Tang_tzb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return iRoleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for(String permissionId:permissionIds){
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void deleteRoleById(String roleId) throws Exception {
        //从user_role表中删除
        iRoleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        iRoleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        iRoleDao.deleteRoleById(roleId);
    }


}
