package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.exception.UserAlreadyExistException;
import com.c_project.auto_occasion.exception.UserNotFoundException;
import com.c_project.auto_occasion.model.User;

import java.util.List;

public interface UserService {
    List<User> getall() throws UserNotFoundException;

    User addUser(User user) throws UserAlreadyExistException;

    User getUserByUserName(String username)  throws UserNotFoundException;
}
