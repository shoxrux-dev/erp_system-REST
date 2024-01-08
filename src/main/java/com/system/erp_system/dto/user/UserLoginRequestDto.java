package com.system.erp_system.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginRequestDto {
    @NotBlank String username;
    @NotBlank String password;
}
