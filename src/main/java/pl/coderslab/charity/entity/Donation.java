package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="INT NULL COMMENT 'Number of sacks'")
    private Integer quantity;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Institution institution;

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

    public Donation(Long id, Integer quantity, Category category, Institution institution, String street, String city, String zipCode, LocalDate pickUpDate, LocalTime pickUpTime, String pickUpComment) {
        this.id = id;
        this.quantity = quantity;
        this.category = category;
        this.institution = institution;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.pickUpComment = pickUpComment;
    }
}
