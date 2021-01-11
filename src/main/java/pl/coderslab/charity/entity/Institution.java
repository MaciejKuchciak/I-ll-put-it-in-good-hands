package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Institution name'")
    private String name;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Institution description'")
    private String description;

    @OneToMany(mappedBy="institution", cascade = CascadeType.REMOVE)
    private List<Donation> donationList = new ArrayList<>();

    public Institution(Long id, String name,String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
