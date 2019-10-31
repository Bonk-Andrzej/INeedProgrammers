package com.bonkAndrzej.iNeedProgrammers.location;

import com.bonkAndrzej.iNeedProgrammers.location.dto.LocationDto;
import com.bonkAndrzej.iNeedProgrammers.location.dto.LocationForm;
import com.bonkAndrzej.iNeedProgrammers.location.exception.LocationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationDto createLocation(LocationForm locationForm) {
        Location location = new Location();
        location.setName(locationForm.getName());

        Location locationAfterSave = locationRepository.save(location);
        return new LocationDto(locationAfterSave);
    }

    public LocationDto updateLocation(LocationForm locationForm, Long id) throws LocationException {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new LocationException("Location not found with given id " + id));

        if (!location.getVersion().equals(locationForm.getVersion()))
            throw new LocationException("OptimisticLockException - wrong location version " + locationForm.getVersion() +
                    "\nExpected " + location.getVersion());

        location.setName(locationForm.getName());
        Location locationAfterUpdate = locationRepository.save(location);
        return new LocationDto(locationAfterUpdate);
    }

    @Transactional(readOnly = true)
    public LocationDto getLocation(Long id) throws LocationException {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new LocationException("Location not found with given id " + id));

        return new LocationDto(location);
    }

    @Transactional(readOnly = true)
    public List<LocationDto> getAllLocations() {
        List<Location> location = locationRepository.findAll();

        return location.stream()
                .map(LocationDto::new).collect(Collectors.toList());
    }

    public void deleteLocation(Long id, Integer version) throws LocationException {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new LocationException("Location not found with given id " + id));

        if (!location.getVersion().equals(version))
            throw new LocationException("OptimisticLockException - wrong location version " + version +
                    "\nExpected " + location.getVersion());

        locationRepository.delete(location);
    }

    @Transactional(readOnly = true)
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
