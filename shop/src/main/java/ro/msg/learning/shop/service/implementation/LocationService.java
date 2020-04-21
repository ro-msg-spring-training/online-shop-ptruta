package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.service.interfaces.ILocationService;

@Service
@RequiredArgsConstructor
public class LocationService implements ILocationService {
    private final LocationRepository locationRepository;
    private final ProductService productService;
    private final StockService stockService;


}
