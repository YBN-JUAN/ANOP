package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.UserRequestResource;

import java.util.List;

public interface CustomUserRequestMapper {
    List<UserRequestResource> listUserRequest(Integer userId);

    List<UserRequestResource> listManageUserRequest(Integer userId);
}