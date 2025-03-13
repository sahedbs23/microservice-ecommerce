package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "post_codes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_code", "police_station"})
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_code", nullable = false, length = 55)
    private String postCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "police_station", nullable = false, columnDefinition = "BIGINT DEFAULT 0", foreignKey = @ForeignKey(name = "fk_police_station_post_code"))
    private PoliceStation policeStation;
}
