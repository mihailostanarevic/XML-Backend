package com.rentacar.CoreAPI.events;

import com.rentacar.CoreAPI.dto.AdSaga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdCreatedEvent {

    private UUID adId;

    private AdSaga adSaga;

}
