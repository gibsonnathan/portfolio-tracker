package com.nathangibson.portfolio.mapper;

import com.nathangibson.portfolio.domain.User;
import com.nathangibson.portfolio.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User mapUserEntityToUser(UserEntity userEntity) {
    User user = new User();
    user.setUsername(userEntity.getUsername());
    user.setId(userEntity.getId());
    return user;
  }
}
