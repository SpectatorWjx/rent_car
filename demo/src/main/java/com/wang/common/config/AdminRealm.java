package com.wang.common.config;

import javax.annotation.Resource;

import com.wang.dao.AdminMapper;
import com.wang.entity.Admin;
import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import org.springframework.beans.factory.annotation.Autowired;


public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminMapper adminDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 把AuthenticationToken转换为CustomizedToken
        CustomizedToken customizedToken = (CustomizedToken) token;
        // 2. 从CustomizedToken中获取username
        String username = customizedToken.getUsername();
        // 3. 若用户不存在，抛出UnknownAccountException异常
        Admin admin = adminDao.selectByName(username);
        if (admin == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, admin.getPassword(),
                getName());
        return info;
    }

}
