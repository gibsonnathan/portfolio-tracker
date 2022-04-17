package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.User;
import com.nathangibson.portfolio.exception.UserNotFoundException;
import com.nathangibson.portfolio.mapper.UserMapper;
import com.nathangibson.portfolio.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public User getUserByUsername(String username) {
    return userMapper.mapUserEntityToUser(
        userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException()));
  }

  public User getUserById(long id) {
    return userMapper.mapUserEntityToUser(userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException()));
  }

}

