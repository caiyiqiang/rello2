package cn.itcast.Controller;

import cn.itcast.IPermissionService;
import cn.itcast.domain.Permission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-22 16:09
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")Integer size)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Permission> permissions = iPermissionService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
    /**
     * 详情
     */
    @RequestMapping("/findByPermissionId.do")
    public ModelAndView findByPermissionId(@RequestParam(name = "id")String permissionId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Permission permission=iPermissionService.findByPermissionId(permissionId);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }



    @RequestMapping("/save.do")
    public String save(Permission permission)throws Exception{
        iPermissionService.save(permission);
        return "redirect:findAll.do";
    }
}
