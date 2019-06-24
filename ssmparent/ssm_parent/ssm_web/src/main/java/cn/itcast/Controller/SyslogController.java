package cn.itcast.Controller;

import cn.itcast.ISyslogService;
import cn.itcast.domain.Syslog;
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
 * @create: 2019-06-23 19:27
 */
@Controller
@RequestMapping("/sysLog")
public class SyslogController {

    @Autowired
    private ISyslogService iSyslogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")Integer size) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Syslog> sysLogs = iSyslogService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(sysLogs);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
