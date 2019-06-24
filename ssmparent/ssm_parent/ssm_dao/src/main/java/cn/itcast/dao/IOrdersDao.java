package cn.itcast.dao;

import cn.itcast.domain.Member;
import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-19 18:29
 */
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({@Result(id = true, property = "id", column = "id"),
              @Result(property = "orderNum", column = "orderNum"),
              @Result(property = "orderTime",column = "orderTime"),
              @Result(property = "personCount",column = "personCount"),
              @Result(property = "orderDesc",column = "orderDesc"),
              @Result(property = "payType",column = "payType"),
              @Result(property = "orderStatus",column = "orderStatus"),
              @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "cn.itcast.dao.IProductDao.findById"))

    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{ordersId}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "personCount",column = "personCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "cn.itcast.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select="cn.itcast.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.dao.ITraveller.findByOrdersId"))
    })
    Orders findById(String ordersId) throws Exception;

    @Select("select * from orders where id=#{ordersId}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "personCount",column = "personCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "cn.itcast.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select="cn.itcast.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.dao.ITraveller.findByOrdersId"))
    })
    Orders findOrderById(String orderId)throws Exception;
}
