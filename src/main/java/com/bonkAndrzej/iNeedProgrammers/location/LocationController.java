package com.bonkAndrzej.iNeedProgrammers.location;

import com.bonkAndrzej.iNeedProgrammers.location.dto.LocationDto;
import com.bonkAndrzej.iNeedProgrammers.location.dto.LocationForm;
import com.bonkAndrzej.iNeedProgrammers.location.exception.LocationException;
import com.bonkAndrzej.iNeedProgrammers.user.role.roleAnnotation.Admin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class LocationController {

    private final LocationService locationService;

    @Admin
    @PostMapping("/locations")
    public ResponseEntity<LocationDto> createLocation(@Valid @RequestBody LocationForm locationForm) {
        LocationDto locationDto = locationService.createLocation(locationForm);

        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }


    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationDto> getLocation(@Positive @PathVariable Long id) throws LocationException {
        LocationDto locationDto = locationService.getLocation(id);

        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDto>> getAllLocation() {
        List<LocationDto> locationDto = locationService.getAllLocations();

        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    @Admin
    @PutMapping("/locations/{id}")
    public ResponseEntity<LocationDto> updateLocation(@Valid @RequestBody LocationForm locationForm, @Positive @PathVariable Long id)
            throws LocationException {
        LocationDto locationDto = locationService.updateLocation(locationForm, id);

        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    @Admin
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@Positive @PathVariable Long id, @PositiveOrZero @RequestParam Integer version)
            throws LocationException {
        locationService.deleteLocation(id, version);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
