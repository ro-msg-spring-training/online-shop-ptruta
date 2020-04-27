package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;

@Component
public class LocationConverter extends BaseConverter<Location, LocationDto> {
    @Override
    public Location convertDtoToModel(LocationDto dto) {
        return Location.builder()
                .id(dto.getId())
                .name(dto.getName())
                .addressCity(dto.getCity())
                .addressCountry(dto.getCountry())
                .addressCounty(dto.getCounty())
                .addressStreetAddress(dto.getStreetAddress())
                .build();
    }

    @Override
    public LocationDto convertModelToDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .city(location.getAddressCity())
                .country(location.getAddressCountry())
                .county(location.getAddressCounty())
                .streetAddress(location.getAddressStreetAddress())
                .build();
    }
}
