package com.db;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@EqualsAndHashCode(of = {"id","name","dateOfBirth","cityName","countryName"})
@Entity
@Table(name = "flat_users")
public class FlatUserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id ;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "county_name")
    private String countryName;

    public FlatUserEntity() {
    }
}
