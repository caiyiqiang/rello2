package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-21 15:48
 */
public interface IUserDao {


    @Select("select * from users where username=#{username}")
    @Results({@Result(id = true, property = "id", column = "id"),
              @Result(property = "username",column = "username"),
              @Result(property = "email",column = "email"),
              @Result(property = "password",column = "password"),
              @Result(property = "phoneNum",column = "phoneNum"),
              @Result(property = "status",column = "status"),
              @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findByUsername(String username) throws Exception;


    @Select("select * from users")
    List<UserInfo> findAll()throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status)values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo user)throws Exception;


    @Select("select * from users where id=#{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id)throws Exception;


    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByUserId(String userId)throws Exception;


    @Insert("insert into users_role(userId,roleId)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId)throws Exception;

    @Select("select * from users where id in(select userId from users_role where roleId=#{RoleId})")
    List<UserInfo> findUserByRoleId(String RoleId)throws Exception;

    @Delete("delete from users_role where roleId=#{roleId}")
    void delete(String roleId)throws Exception;
}
