package com.duongprj.logistic_service.entity;

import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
public class WorkUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String code;
    String address;

    @Enumerated(EnumType.STRING)
    UnitType type;

    @Enumerated(EnumType.STRING)
    Region region;
}
