package cn.itcast.impl;

import cn.itcast.IUserService;
import cn.itcast.dao.IUserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.utils.BCryptPasswordEncoderUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-21 12:57
 */
@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService{

    @Autowired
    private IUserDao iUserDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserInfo userInfo=null;
        try {

            userInfo=iUserDao.findByUsername(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
         User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()
                ==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;

    }
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);
        return iUserDao.findAll();
    }

    //对密码加密
    @Override
    public void save(UserInfo user) throws Exception{
        user.setPassword(BCryptPasswordEncoderUtils.encodePassword(user.getPassword()));
        iUserDao.save(user);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return iUserDao.findById(id);
    }

    @Override
    public List<Role> findRoleByUserId(String userId) throws Exception{
        return iUserDao.findRoleByUserId(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception{
        for (String roleId : roleIds) {
            iUserDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void delete(String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            iUserDao.delete(roleId);
        }
    }
}
