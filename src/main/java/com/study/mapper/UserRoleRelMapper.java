package com.study.mapper;

import com.study.domain.UserRoleRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("rid") Integer rid);

    int deleteByUid(int uid);

    int insert(UserRoleRel record);

    List<UserRoleRel> selectAll();

    List<UserRoleRel> selectByRid(int rid);

}