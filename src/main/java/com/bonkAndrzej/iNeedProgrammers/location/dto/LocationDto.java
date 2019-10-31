package com.bonkAndrzej.iNeedProgrammers.location.dto;

import com.bonkAndrzej.iNeedProgrammers.location.Location;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private String uuid;
    private String name;
    private Integer version;

    public LocationDto(Location location) {
        this.id = location.getId();
        this.uuid = location.getUuid().toString();
        this.name = location.getName();
        this.version = location.getVersion();
    }
}
