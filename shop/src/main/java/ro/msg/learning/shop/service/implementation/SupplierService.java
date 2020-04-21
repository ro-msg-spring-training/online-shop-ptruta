package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.SupplierConverter;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.interfaces.ISupplierService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public Optional<SupplierDto> getSupplier(final Integer id) {
        Supplier supplier = supplierRepository.getOne(id);
        SupplierConverter supplierConverter = new SupplierConverter();
        return Optional.ofNullable(supplierConverter.convertModelToDto(supplier));
    }
}
