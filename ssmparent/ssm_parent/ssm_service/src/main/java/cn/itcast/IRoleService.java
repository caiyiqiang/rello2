package cn.itcast;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-22 14:13
 */
public interface IRoleService {

    public List<Role> findAll() throws Exception;

    void save(Role role)throws Exception;

    Role findById(String roleId)throws Exception;

    List<Permission> findRoleByIdAndAllPermission(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;

    Role findByRoleId(String roleId)throws Exception;

    List<Role> findRoleByIdAndUserId(String userId)throws Exception;
}
