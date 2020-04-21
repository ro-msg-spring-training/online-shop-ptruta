package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;

public class LocationConverter extends BaseConverter<Location, LocationDto> {
    @Override
    public Location convertDtoToModel(LocationDto dto) {
        Location location = Location.builder()
                .name(dto.getName())
                .addressCity(dto.getCity())
                .addressCountry(dto.getCountry())
                .addressCounty(dto.getCounty())
                .addressStreetAddress(dto.getStreetAddress())
                .build();
        location.setId(dto.getId());
        return location;
    }

    @Override
    public LocationDto convertModelToDto(Location location) {
        LocationDto locationDto = LocationDto.builder()
                .name(location.getName())
                .city(location.getAddressCity())
                .country(location.getAddressCountry())
                .county(location.getAddressCounty())
                .streetAddress(location.getAddressStreetAddress())
                .build();
        locationDto.setId(locationDto.getId());
        return locationDto;
    }
}
