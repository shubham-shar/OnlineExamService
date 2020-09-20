package com.assignment.OnlineExamService.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Examiner extends MandatoryFields implements UserDetails {
    
    @Column(unique = true)
    private String username;
    
    @JsonIgnore
    private String password;
    
    private String firstname;
    
    private String lastname;
    
    private Boolean enabled = true;
    
    private String salt = "randomText";
    
    private String role = "USER";
    
    @JsonIgnore
    @OneToMany(mappedBy = "examiner")
    private List<Tests> tests;
    
    @JsonIgnore
    @OneToMany(mappedBy = "examiner")
    private List<Result> results;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
