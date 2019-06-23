package cn.itcast.Controller;

import cn.itcast.IRoleService;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
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
 * @create: 2019-06-22 14:10
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Role> roleList = iRoleService.findAll();

        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }
    /**
     * 详情
     */

    @RequestMapping("/findByRoleId.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String RoleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Role roles=iRoleService.findByRoleId(RoleId);
        mv.addObject("roles",roles);
        mv.setViewName("role-show");
        return mv;
    }
    /**
     * 角色关联权限
     */

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id")String roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Role role= iRoleService.findById(roleId);
        List<Permission> permissionList=iRoleService.findRoleByIdAndAllPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 给角色添加权限
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "role",required = true)String roleId,@RequestParam(name = "ids",
    required = true)String[] permissionIds)throws Exception{
        iRoleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        iRoleService.save(role);
        return "redirect:findAll.do";
    }
}
