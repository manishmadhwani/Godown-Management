package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name = "ENTRIES")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entry {
    @ManyToOne
    @JoinColumn(name = "itemId")
    Item item;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long entryId;

    int noOfSacks;
    int costPerSack;
    int entryValuation;
    Date entryDate;

    @ManyToOne
    @JoinColumn(name = "godownId")
    Godown godown;
}
