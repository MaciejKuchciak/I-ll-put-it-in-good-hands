package pl.coderslab.charity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="INT NULL COMMENT 'Number of sacks'")
    private Integer quantity;

    @ManyToMany
    List<Category> categories = new ArrayList<>();

    @ManyToOne
    private Institution institution;

    @Column(columnDefinition="INT NULL COMMENT 'Phone'")
    private Integer phone;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Street'")
    private String street;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'City'")
    private String city;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Zip code'")
    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition="DATE NULL COMMENT 'Pick up date'")
    private LocalDate pickUpDate;

    @Column(columnDefinition="TIME NULL COMMENT 'Pick up time'")
    private LocalTime pickUpTime;

    @Column(columnDefinition="VARCHAR(255) NULL COMMENT 'Pick up comment'")
    private String pickUpComment;

}
