package cn.itcast.impl;

import cn.itcast.ISyslogService;
import cn.itcast.dao.ISyslogDao;
import cn.itcast.domain.Syslog;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-23 17:31
 */
@Service
@Transactional
public class ISyslogServiceImpl implements ISyslogService {

    @Autowired
    private ISyslogDao iSyslogDao;
    @Override
    public void save(Syslog syslog) throws Exception {
        iSyslogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return iSyslogDao.findAll();
    }
}
