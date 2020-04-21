package ro.msg.learning.shop.service.interfaces;


import ro.msg.learning.shop.dto.SupplierDto;

import java.util.Optional;

public interface ISupplierService {
    Optional<SupplierDto> getSupplier(final Integer id);
}
