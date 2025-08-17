package com.springSecurityP1.SpringSecurity.utils;

import com.springSecurityP1.SpringSecurity.entity.enums.Permissions;
import com.springSecurityP1.SpringSecurity.entity.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springSecurityP1.SpringSecurity.entity.enums.Permissions.*;
import static com.springSecurityP1.SpringSecurity.entity.enums.Role.*;

public class PermissionMapping {
    private static final Map<Role, Set<Permissions>> map = Map.of(
            USER, Set.of(POST_CREATE,POST_DELETE),
            ADMIN,Set.of(POST_VIEW,POST_UPDATE,USER_VIEW)
    );

    public static Set<SimpleGrantedAuthority> getAuthorityForRole(Role role){
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
