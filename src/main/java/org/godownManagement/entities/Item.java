package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "ITEMS")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    long itemId;

    @Column(name = "comodity")
    String comodity;

    @Column(name = "type")
    String type;

    @Column(name = "markaName")
    String markaName;

    @Column(name = "packing")
    int packing;

    @Column(name = "addressFrom")
    String addressFrom;
}
