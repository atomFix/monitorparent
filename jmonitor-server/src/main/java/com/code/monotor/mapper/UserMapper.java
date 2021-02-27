package com.code.monotor.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/24 15:23
 */
@Mapper
@Repository
public interface UserMapper {

    @Insert("insert into user (name) values (#{name})")
    void insertUser(@Param("name") String name);

}
