package cn.itcast;

import cn.itcast.domain.Syslog;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-23 17:29
 */
public interface ISyslogService {


    public void save(Syslog syslog) throws Exception;

    public List<Syslog> findAll(Integer page,Integer size) throws Exception;

}
