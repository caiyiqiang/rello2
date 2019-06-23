package cn.itcast.dao;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-18 18:28
 */
public interface IProductDao {

    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
