package cn.itcast.Controller;

import cn.itcast.IRoleService;
import cn.itcast.IUserService;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-21 19:32
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
    @RequestParam(name = "size",defaultValue = "4")Integer size)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<UserInfo> user =iUserService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(user);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=iUserService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
        /**
         * 用户关联角色
         */

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String userId)throws Exception{
        ModelAndView mv=new ModelAndView();
        UserInfo user = iUserService.findById(userId);
        List<Role> roleList=iUserService.findRoleByUserId(userId);
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }
    /**
     * 给用户添加角色
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId")String userId,@RequestParam(name = "ids")String[] roleIds)throws Exception{
        iUserService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
    /**
     * 查询用户拥有的角色
     */
    @Autowired
    private IRoleService iRoleService;
    @RequestMapping("/findRoleByIdAndUserId.do")
    public ModelAndView findRoleByIdAndUserId(@RequestParam(name = "id")String userId)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Role> roles= iRoleService.findRoleByIdAndUserId(userId);

        mv.addObject("roles",roles);
        mv.setViewName("user-role-delete");
        return mv;
    }
    @RequestMapping("/deleteRoleToUser.do")
    public String delete(@RequestParam(name = "ids")String[] roleIds)throws Exception{
        iUserService.delete(roleIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/save.do")
    public String save(UserInfo user) throws Exception{
      if(user!=null){
          iUserService.save(user);
      }
      return "redirect:findAll.do";
    }
    /**
     * 条件查询
     */

    @RequestMapping("/findAllByCondition.do")
    public ModelAndView findAllByCondition()throws Exception{
        ModelAndView mv=new ModelAndView();


        return mv;
    }
}
