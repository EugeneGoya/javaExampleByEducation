package com.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "flat_users")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.models.FlatUserEntity.findAll",
                        query = "SELECT f FROM FlatUserEntity f"
                )
        })
public class FlatUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "city_name", nullable = false)
    private String cityName;
    @Column(name = "county_name", nullable = false)
    private String countryName;
}
