package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "CITIES")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int cityId;

    String name;
    String state;

    @OneToMany(mappedBy = "city")
    List<Godown> godowns;
}
