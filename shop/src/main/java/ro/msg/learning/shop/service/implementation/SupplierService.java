package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.SupplierConverter;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Supplier getSupplier(final Integer id) {
        return supplierRepository.findById(id).orElse(null);
    }
}
