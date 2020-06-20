package com.rentacar.rentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report extends BaseEntity {

    private String description;

    private String kilometersTraveled;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_ad_id", referencedColumnName = "id")
    private RequestAd requestAd;

}
