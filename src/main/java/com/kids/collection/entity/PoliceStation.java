package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "police_station")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PoliceStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 55)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false, columnDefinition = "BIGINT DEFAULT 0", foreignKey = @ForeignKey(name = "fk_district_police_station"))
    private District district;
}
