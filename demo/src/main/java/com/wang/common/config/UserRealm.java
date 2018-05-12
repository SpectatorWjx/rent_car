package com.wang.common.config;

import com.wang.dao.UserMapper;
import com.wang.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {        // 根据用户情况，来构建AuthenticationInfo对象并返回,通常使用的是SimpleAuthenticationInfo
        // 1.把AuthenticationToken转换成UsernamePasswordToken
        CustomizedToken upToken = (CustomizedToken) token;

        // 2.从UsernamePasswordToken中 获取username
        String username = upToken.getUsername();

        User user = userDao.selectByPhone(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        Session session = SecurityUtils.getSubject().getSession();


        session.setAttribute("retrytimes",0);
        session.setAttribute("userInfo", user);

        //构建返回对象，将用户名，密码，当前realm的名称提供给shiro做加密认证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username ,user.getPassword(),getName());
        return info;
    }



}
