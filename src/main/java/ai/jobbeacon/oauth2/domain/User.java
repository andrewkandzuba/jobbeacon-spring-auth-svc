package ai.jobbeacon.oauth2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String userId;
    @Column(name = "user_name", unique = true)
    @NonNull
    private String username;
    @NonNull
    @JsonIgnore
    private String password;

    public User() {}

    public User(String userId, @NonNull String username, @NonNull String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @NonNull
    public String getUsername() {
        return username;
    }
    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
    public void setPassword(@NonNull String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return STR."User{userId='\{userId}\{'\''}, username='\{username}\{'\''}, password='\{password}\{'\''}\{'}'}";
    }
}