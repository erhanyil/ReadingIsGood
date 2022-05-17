package com.example.readingisgood.model;

import com.example.readingisgood.constant.Roles;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Table
@Entity
@Data
public class CustomerModel extends BaseModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "Lastname is mandatory")
    private String lastName;

    @Column(nullable = false)
    @NotEmpty(message = "Password is mandatory")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Role is mandatory")
    private String role = Roles.DEFAULT_USER.name();

    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<OrderModel> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
