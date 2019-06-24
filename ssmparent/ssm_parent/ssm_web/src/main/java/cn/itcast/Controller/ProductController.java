package cn.itcast.Controller;

import cn.itcast.IProductService;
import cn.itcast.domain.Product;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-18 19:08
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    /**
     * 保存
     */
    @RequestMapping("/save.do")
    public String save(Product product) {
        service.save(product);
        return "redirect:findAll.do";
    }
    /**
     * 查询所有商品
     */
    @RequestMapping("/findAll.do")
    //此注解表示只有"ROLE_USER"角色的用户能访问此方法
    @Secured("ROLE_USER")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Product> products = service.findAll(page,size);
        PageInfo pageInfo=new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
}
