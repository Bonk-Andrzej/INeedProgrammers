package com.bonkAndrzej.iNeedProgrammers.location.dto;

import com.bonkAndrzej.iNeedProgrammers.location.Location;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class LocationDto {

    private String name;
    private Integer version;

    public LocationDto(Location location) {
        this.name = location.getName();
        this.version = location.getVersion();
    }
}
