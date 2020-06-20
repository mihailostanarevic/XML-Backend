package com.rentacar.authentificationservice.entity;

import com.rentacar.authentificationservice.util.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "user_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {

    @Column(unique = true, nullable = false)
    private String username; //email

    @Column(nullable = false)
    private String password;

    private UserRole userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Agent agent;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Admin admin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private SimpleUser simpleUser;

    private boolean deleted;

    private boolean hasSignedIn;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth_list = new ArrayList<>();
        this.authorities.forEach(authority -> auth_list.addAll(authority.getPermissions()));
        return auth_list;
    }

//    private List<UUID> messagesSent = new ArrayList<>();
//
//    private List<UUID> messagesReceived = new ArrayList<>();

    public Set<Authority> getRoles() {
        return this.authorities;
    }
}
