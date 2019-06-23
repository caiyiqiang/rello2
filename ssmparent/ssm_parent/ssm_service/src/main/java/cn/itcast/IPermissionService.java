package cn.itcast;

import cn.itcast.domain.Permission;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-22 16:11
 */
public interface IPermissionService {

    public List<Permission> findAll(int page,int size)throws Exception;

    void save(Permission permission)throws Exception;

    Permission findByPermissionId(String permissionId)throws Exception;
}
