package cn.itcast;

import cn.itcast.domain.Product;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-18 18:31
 */
public interface IProductService {

    public List<Product> findAll(int page,int size) throws Exception;

    void save(Product product);
}
