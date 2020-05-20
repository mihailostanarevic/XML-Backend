package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.config.DateTimeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity {

    private double value;       // procenat

    private DateTimeConfig dateOfPublication;

    private boolean deleted;

}
