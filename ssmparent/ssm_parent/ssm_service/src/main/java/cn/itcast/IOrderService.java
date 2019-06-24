package cn.itcast;

import cn.itcast.domain.Orders;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-19 18:16
 */
public interface IOrderService {

    List<Orders> findAll(int page,int pageSize) throws Exception;

    Orders findById(String ordersId)throws Exception;

    Orders findOrderById(String orderId)throws Exception;


}
