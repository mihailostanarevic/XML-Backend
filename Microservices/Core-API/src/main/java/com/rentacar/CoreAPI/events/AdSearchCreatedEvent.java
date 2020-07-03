package com.rentacar.CoreAPI.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdSearchCreatedEvent {

    private String adAggregateId;

}
