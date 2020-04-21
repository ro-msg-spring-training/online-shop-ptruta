package ro.msg.learning.shop.converter;


import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.SupplierDto;

@Component
public class SupplierConverter extends BaseConverter<Supplier, SupplierDto> {

    @Override
    public Supplier convertDtoToModel(SupplierDto dto) {
        Supplier supplier = Supplier.builder()
                .name(dto.getSupplierName())
                .build();
        supplier.setId(dto.getId());
        return supplier;
    }

    @Override
    public SupplierDto convertModelToDto(Supplier supplier) {
        SupplierDto supplierDto = SupplierDto.builder()
                .supplierName(supplier.getName())
                .build();
        supplierDto.setId(supplier.getId());
        return supplierDto;
    }
}
