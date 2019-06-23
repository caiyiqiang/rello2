package cn.itcast.impl;

import cn.itcast.IOrderService;
import cn.itcast.dao.IOrdersDao;
import cn.itcast.domain.Orders;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-19 18:16
 */
@Service
public class IOrderServiceImpl implements IOrderService{
    @Autowired
    private IOrdersDao iOrdersDao;
    @Override
    public List<Orders> findAll(int page,int pageSize) throws Exception{

        PageHelper.startPage(page,pageSize);
        return iOrdersDao.findAll();

    }

    @Override
    public Orders findById(String ordersId) throws Exception{
        return iOrdersDao.findById(ordersId);
    }
}
