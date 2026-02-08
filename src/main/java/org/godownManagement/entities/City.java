package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.util.List;

@Table(name = "CITIES")
@Data
public class City {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int cityId;

    String name;
    String state;

    @OneToMany(mappedBy = "city")
    List<Godown> godowns;

    City(String name, List<Godown> godowns) {
        this.name = name;
        this.godowns = godowns;
    }
}
