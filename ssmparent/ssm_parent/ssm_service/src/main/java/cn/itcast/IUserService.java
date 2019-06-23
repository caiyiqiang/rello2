package cn.itcast;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-21 12:55
 */
public interface IUserService extends UserDetailsService{

    List<UserInfo> findAll(int page,int size)throws Exception;


    void save(UserInfo user)throws Exception;

    UserInfo findById(String id)throws Exception;

    List<Role> findRoleByUserId(String userId)throws Exception;

    void addRoleToUser(String userId, String[] roleIds)throws Exception;

    void delete(String[] roleIds)throws Exception;
}
