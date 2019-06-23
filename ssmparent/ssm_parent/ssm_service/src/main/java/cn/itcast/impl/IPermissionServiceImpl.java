package cn.itcast.impl;

import cn.itcast.IPermissionService;
import cn.itcast.dao.IPermissionsDao;
import cn.itcast.domain.Permission;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-22 16:12
 */
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionsDao iPermissionsDao;
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return iPermissionsDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception{
        iPermissionsDao.save(permission);
    }

    @Override
    public Permission findByPermissionId(String permissionId) throws Exception {
        return iPermissionsDao.findByPermission(permissionId);
    }
}
