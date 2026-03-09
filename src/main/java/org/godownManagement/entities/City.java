package org.godownManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "CITIES")
@Getter
@Setter
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
    @JsonIgnore
    List<Godown> godowns;
}
