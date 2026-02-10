package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name = "ENTRIES")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long entryId;

    int noOfPackings;
    int entryValuation;
    Date entryDate;

    @ManyToOne
    @JoinColumn(name = "godownId")
    Godown godown;

    @OneToOne
    @JoinColumn(name = "itemId")
    Item item;
}
