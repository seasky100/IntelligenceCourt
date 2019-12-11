package com.study.web.realm;

import com.study.domain.User;
import com.study.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*System.out.println("来到了认证");*/
        String username = (String)authenticationToken.getPrincipal();
        /*System.out.println(username);*/
        User userRes = userService.selectByUserName(username);
        if(userRes == null){
            return null;
        }
        /*认证*/
        /*参数：主体，凭证（正确的密码），盐（散列加密用的），当前realm名称*/
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userRes, userRes.getPassword(), this.getName());
        return simpleAuthenticationInfo;
    }

    /*
    * 授权
    * web doGetAuthorizationInfo什么时候调用
    * 1.发现访问路径对应的方法上面有授权注解 就会调用doGetAuthorizationInfo
    * 2.页面当中有授权注解 也会调用doGetAuthorizationInfo
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权调用");
        /*获取用户身份信息*/
        User user = (User)principalCollection.getPrimaryPrincipal();
        /*根据当前用户查询角色和权限*/
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> permissions = new ArrayList<>();
        /*查询角色*/
        roles = userService.getRolesByUid(user.getUid());
        System.out.println("roles====="+roles);
        /*查询权限*/
        permissions = userService.getPermissionByUid(user.getUid());
        System.out.println("roles====="+permissions);

        /*给授权信息*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }


}
