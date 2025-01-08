package ai.jobbeacon.oauth2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

@Entity
@Table(name = "T_USER", uniqueConstraints = {@UniqueConstraint(columnNames = "user_name")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long userId;

    @Column(name = "user_name", unique = true)
    @NotNull
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    @NotNull
    private String password;

    @Column(name = "granted_authority")
    @ColumnDefault("ROLE_USER")
    @Generated(event = EventType.INSERT)
    private String grantedAuthority;

    public User() {}

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(@NonNull String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(@NonNull String password) {
        this.password = password;
    }
    public String getGrantedAuthority() {
        return grantedAuthority;
    }
    public void setGrantedAuthority(@NonNull String grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }
}