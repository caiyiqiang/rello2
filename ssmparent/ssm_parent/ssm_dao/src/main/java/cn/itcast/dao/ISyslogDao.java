package cn.itcast.dao;

import cn.itcast.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-23 17:48
 */
public interface ISyslogDao {

    @Insert("insert into syslog(ip,username,visitTime,executionTime,method,url)values(#{ip},#{username},#{visitTime},#{executionTime},#{method},#{url})")
    public void save(Syslog syslog) throws Exception;

    @Select("select * from syslog")
    List<Syslog> findAll()throws Exception;

}
