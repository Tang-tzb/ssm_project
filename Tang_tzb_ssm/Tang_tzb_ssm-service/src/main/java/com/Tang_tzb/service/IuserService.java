package com.Tang_tzb.service;

import com.Tang_tzb.domain.Role;
import com.Tang_tzb.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IuserService extends UserDetailsService{

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo ) throws Exception;


    void addRoleToUser(String userId, String[] roleIds);

    UserInfo findById(String userid) throws Exception;

    List<Role> findOtherRoles(String userid);
}
