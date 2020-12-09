package maosi.mapper;

import maosi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where account = #{account}")
    User getUser (String account);
}
