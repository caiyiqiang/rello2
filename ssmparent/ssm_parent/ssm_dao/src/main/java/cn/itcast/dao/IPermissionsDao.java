package cn.itcast.dao;

import cn.itcast.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-22 11:10
 */
public interface IPermissionsDao {

    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{RoleId})")
    public List<Permission> findPermissionByRoleId(String RoleId) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url)values(#{permissionName},#{url})")
    void save(Permission permission)throws Exception;

    @Select("select * from permission where id=#{permissionId}")
    @Results({@Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.dao.IRoleDao.findRoleByPermission"))
    })
    Permission findByPermission(String permissionId)throws Exception;
}
