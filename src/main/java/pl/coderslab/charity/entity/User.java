package pl.coderslab.charity.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@ToString(exclude = "password")
@EqualsAndHashCode(of = "username")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="VARCHAR(255) NOT NULL COMMENT 'Username'")
    private String username;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'E-mail address'")
    private String email;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'First name'")
    private String firstName;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Last name'")
    private String lastName;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Password'")
    private String password;

    @Column(columnDefinition="TINYINT(1) NULL COMMENT '0 account disabled / 1 account enabled'")
    private int enable = 1;

    @ManyToOne
    private UserRoles userRoles;

    public User(Long id, String username, String email, String firstName, String lastName, String password, int enable) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enable = enable;
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
                ", enable=" + enable +
                '}';
    }
}

