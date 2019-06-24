package cn.itcast.impl;



import cn.itcast.IProductService;

import cn.itcast.dao.IProductDao;
import cn.itcast.domain.Product;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-18 18:48
 */
@Service
@Transactional
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return iProductDao.findAll();
    }

    @Override
    public void save(Product product){
        iProductDao.save(product);
    }
}
