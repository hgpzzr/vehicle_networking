package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    User getUserByUserName(String userName);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}