package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.LocationConverter;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final LocationConverter locationConverter;
    private final StockRepository stockRepository;


    public LocationDto getLocation(Integer id) throws LocationIdNotFoundException {
        return locationRepository.findById(id)
                .map(locationConverter::convertModelToDto)
                .orElseThrow(() -> new LocationIdNotFoundException("No location with id" + id));
    }


    public List<LocationDto> getLocations() {
        return locationRepository.findAll()
                .stream()
                .map(locationConverter::convertModelToDto)
                .collect(Collectors.toList());
    }


    public Integer getShippedQuantity(Integer productId, LocationDto location, Integer totalQuantity) throws LocationIdNotFoundException {
        productRepository.findById(productId)
                .orElseThrow(() -> new LocationIdNotFoundException("No product with id" + productId));
        Integer stockGot = stockRepository.findByProductIdAndLocationId(productId, location.getId()).getQuantity();
        return (stockGot < totalQuantity) ? stockGot : totalQuantity;
    }
}
