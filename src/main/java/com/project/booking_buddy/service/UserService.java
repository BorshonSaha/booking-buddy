package com.project.booking_buddy.service;

import com.project.booking_buddy.dto.ProfileUpdateRequestDto;
import com.project.booking_buddy.dto.UserDto;
import com.project.booking_buddy.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
