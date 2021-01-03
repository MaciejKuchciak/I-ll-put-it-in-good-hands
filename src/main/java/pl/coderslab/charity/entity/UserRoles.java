package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String description;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="userRoles")
    private List<User> userList = new ArrayList<>();


    public UserRoles(Long id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
