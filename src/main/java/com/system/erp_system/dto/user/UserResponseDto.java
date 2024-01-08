package com.system.erp_system.dto.user;

import com.system.erp_system.constant.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    String username;
    Set<RoleEnum> roles;
    String image;
    String token;
}
