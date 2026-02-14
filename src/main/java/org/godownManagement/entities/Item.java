package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ITEMS")
@Data
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

    @Column(name = "markaName")
    String markaName;

    @Column(name = "packing")
    int packing;

    @Column(name = "addressFrom")
    String addressFrom;

    @ManyToOne
    @JoinColumn(name = "owner_Id")
    User owner;
}
