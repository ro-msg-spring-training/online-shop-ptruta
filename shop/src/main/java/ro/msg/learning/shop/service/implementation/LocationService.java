package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.LocationConverter;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;


    public Location getLocation(Integer id) throws LocationIdNotFoundException {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationIdNotFoundException("No location with id" + id));
    }


    public List<Location> getLocations() {
        return new ArrayList<>(locationRepository.findAll());
    }
}
