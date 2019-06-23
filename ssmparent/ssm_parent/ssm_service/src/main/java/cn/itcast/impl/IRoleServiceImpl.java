package cn.itcast.impl;

import cn.itcast.IRoleService;
import cn.itcast.dao.IRoleDao;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-22 14:14
 */
@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findById(roleId);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception {
        return iRoleDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {

        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public Role findByRoleId(String roleId) throws Exception {
        return iRoleDao.findByRoleId(roleId);
    }

    @Override
    public List<Role> findRoleByIdAndUserId(String userId) throws Exception {

            return iRoleDao.findRoleByIdAndUserId(userId);

    }
}
