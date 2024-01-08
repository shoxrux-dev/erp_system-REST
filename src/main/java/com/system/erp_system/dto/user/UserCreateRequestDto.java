package com.system.erp_system.dto.user;

import com.system.erp_system.constant.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDto {
    @NotBlank
    String username;
    @NotEmpty
    Set<RoleEnum> roles;
    @NotBlank
    String password;
}
