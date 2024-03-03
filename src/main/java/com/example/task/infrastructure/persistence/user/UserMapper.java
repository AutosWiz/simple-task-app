package com.example.task.infrastructure.persistence.user;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Update("UPDATE users_sequence SET id = LAST_INSERT_ID(id + 1)")
    boolean updateSequence();

    @Select("SELECT LAST_INSERT_ID()")
    int selectSequence();

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<UserDataModel> selectById(Integer id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    Optional<UserDataModel> selectByName(String username);

    @Select("SELECT * FROM users")
    List<UserDataModel> selectAll();

    @Insert({"""
            INSERT INTO users(id, username, password)
            VALUES(#{id}, #{username}, #{password})
            """})
    boolean insert(UserDataModel userDataModel);

    @Update("""
            UPDATE users SET username = #{username}
            WHERE id = #{id}
            """)
    boolean update(UserDataModel userDataModel);

    @Delete("DELETE FROM users WHERE id = #{id}")
    boolean delete(UserDataModel userDataModel);
}
