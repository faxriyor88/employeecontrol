package com.example.employeecontrol.model;

import com.example.employeecontrol.model.absentity.AbstractEntity;
import com.example.employeecontrol.model.enums.Permission;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends AbstractEntity implements UserDetails {
    private String name;
    private String surname;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false,unique = true)
    private String password;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Company company;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissions=this.role.getPermission();
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Permission permission:permissions){
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;
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
        return this.isEnabled;
    }
}
