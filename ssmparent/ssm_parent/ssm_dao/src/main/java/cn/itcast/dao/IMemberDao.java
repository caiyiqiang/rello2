package cn.itcast.dao;

import cn.itcast.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-20 13:55
 */
public interface IMemberDao {

    @Select("select * from member where id=#{id}")
    public Member findById(String id) throws Exception;
}
