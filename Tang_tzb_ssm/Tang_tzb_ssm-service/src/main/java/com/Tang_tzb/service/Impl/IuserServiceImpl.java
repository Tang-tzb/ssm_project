package com.Tang_tzb.service.Impl;

import com.Tang_tzb.dao.IUserDao;
import com.Tang_tzb.domain.Role;
import com.Tang_tzb.domain.UserInfo;
import com.Tang_tzb.service.IuserService;
import com.Tang_tzb.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IuserServiceImpl implements IuserService {
    @Autowired
    IUserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {

            userInfo = userDao.findByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        List<UserInfo> userInfoList = userDao.findAll();
        return userInfoList;
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        String password = BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword());
        userInfo.setPassword(password);
        userDao.save(userInfo);
    }


    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for(String roleId:roleIds){

            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public UserInfo findById(String userid) throws Exception {
        return userDao.findById(userid);
    }

    @Override
    public List<Role> findOtherRoles(String userid) {

        List<Role> otherRoles = userDao.findOtherRoles(userid);
        return otherRoles;
    }


}
