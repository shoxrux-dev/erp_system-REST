package com.system.erp_system.domain;


import com.system.erp_system.constant.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseDomain implements UserDetails {
    @PrimaryKey
    String username;
    String password;
    String image;

    @Frozen
    Set<RoleEnum> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles1 = new ArrayList<>();

        roles.forEach((rol) -> {
            roles1.add(new SimpleGrantedAuthority("ROLE_" + rol));
        });

        return roles1;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
