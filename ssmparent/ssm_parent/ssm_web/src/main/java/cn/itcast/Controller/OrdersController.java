package cn.itcast.Controller;

import cn.itcast.IOrderService;
import cn.itcast.domain.Orders;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-19 17:59
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    /**
     * 订单管理查询带分页
     */
    @Autowired
    private IOrderService iOrderService;
    @RequestMapping("/findAll.do")
    //此注解表示只有ADMIN角色的用户能访问此方法
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true ,defaultValue = "1")Integer page,@RequestParam
            (name = "pageSize",required = true,defaultValue = "4") Integer pageSize ) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList= iOrderService.findAll(page,pageSize);
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }
    /**
     * 详情
     */

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Orders orders= iOrderService.findById(ordersId);
        mv.addObject("orders",orders);

        mv.setViewName("orders-show");
        return mv;
    }
}
