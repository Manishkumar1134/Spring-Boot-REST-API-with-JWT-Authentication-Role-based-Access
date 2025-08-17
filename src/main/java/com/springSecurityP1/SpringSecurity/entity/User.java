package com.springSecurityP1.SpringSecurity.entity;

import com.springSecurityP1.SpringSecurity.entity.enums.Permissions;
import com.springSecurityP1.SpringSecurity.entity.enums.Role;
import com.springSecurityP1.SpringSecurity.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authority = new HashSet<>();
       roles.forEach(
               role ->{
                   Set<SimpleGrantedAuthority> permission = PermissionMapping.getAuthorityForRole(role);
                   authority.addAll(permission);
                   authority.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
               }
       );
       return authority;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
}
