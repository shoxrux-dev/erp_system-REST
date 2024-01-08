package com.system.erp_system.convertor;

import com.system.erp_system.domain.User;
import com.system.erp_system.dto.JwtTokenResponseDto;
import com.system.erp_system.dto.user.UserCreateRequestDto;
import com.system.erp_system.dto.user.UserLoginRequestDto;
import com.system.erp_system.dto.user.UserResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {
    public User convertToDomain(UserCreateRequestDto userCreateRequestDto){
        return User.builder()
                .username(userCreateRequestDto.getUsername())
                .roles(userCreateRequestDto.getRoles())
                .password(userCreateRequestDto.getPassword())
                .build();
    }


    public User convertToDomain(UserLoginRequestDto userLoginRequestDto){
        return User.builder()
                .username(userLoginRequestDto.getUsername())
                .password(userLoginRequestDto.getPassword())
                .build();
    }

    public UserResponseDto from(User user){
        return UserResponseDto.builder()
                .username(user.getUsername())
                .roles(user.getRoles())
                .image(user.getImage())
                .build();
    }

    public JwtTokenResponseDto from(String token){
        return JwtTokenResponseDto.builder()
                .token(token)
                .build();
    }

}
