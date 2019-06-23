package cn.itcast.dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-21 17:57
 */
public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId=#{UserId})")
    @Results({@Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.dao.IPermissionsDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String UserId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll()throws Exception;

    @Insert("insert into role(roleName,roleDesc)values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId)throws Exception;

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(String roleId)throws Exception;

    @Insert("insert into role_permission(roleId,permissionId)values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId)throws Exception;


    @Select("select * from role where id=#{roleId}")
    @Results({@Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select ="cn.itcast.dao.IPermissionsDao.findPermissionByRoleId")),
            @Result(property = "users",column = "id",javaType = java.util.List.class,many  =@Many(select = "cn.itcast.dao.IUserDao.findUserByRoleId"))
    })
    Role findByRoleId(String roleId)throws Exception;


    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByIdAndUserId(String userId)throws Exception;


    @Select("select * from role where id in(select roleId from role_permission where permissionId=#{permissionId})")
    List<Role> findRoleByPermission(String permissionId)throws Exception;
}
