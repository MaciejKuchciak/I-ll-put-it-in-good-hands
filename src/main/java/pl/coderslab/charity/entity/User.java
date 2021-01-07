package pl.coderslab.charity.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "email")
@ToString(exclude = "password")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="VARCHAR(255) COMMENT 'Username'", nullable = false)
    private String username;
    @Column(columnDefinition="VARCHAR(255) COMMENT 'E-mail address'", nullable = false)
    private String email;
    @Column(columnDefinition="VARCHAR(255) COMMENT 'First name'", nullable = false)
    private String firstName;
    @Column(columnDefinition="VARCHAR(255) COMMENT 'Last name'", nullable = false)
    private String lastName;
    @Column(columnDefinition="VARCHAR(255) COMMENT 'Password'", nullable = false, unique = true)
    private String password;
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime last_update;
    private boolean active;
    @ManyToOne
    private UserRoles userRoles;

    public User(Long id, String username, String email, String firstName, String lastName, String password, LocalDateTime created, LocalDateTime last_update, boolean active, UserRoles userRoles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.created = created;
        this.last_update = last_update;
        this.active = active;
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", created='" + created + '\'' +
                ", last_update='" + last_update + '\'' +
                ", active=" + active +
                ", userRoles=" + userRoles +
                '}';
    }
}


