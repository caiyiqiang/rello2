package cn.itcast.dao;

import cn.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-20 13:58
 */
public interface ITraveller {

//   @Select("select * from traveller where id in(select travellerId from order_traveller where orderId =#{ordersId})")
//    public List<Traveller> findById(String ordersId) throws Exception;
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
