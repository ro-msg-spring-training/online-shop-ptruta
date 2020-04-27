package ro.msg.learning.shop.converter;


import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.SupplierDto;

@Component
public class SupplierConverter extends BaseConverter<Supplier, SupplierDto> {

    @Override
    public Supplier convertDtoToModel(SupplierDto dto) {
        return Supplier.builder()
                .name(dto.getSupplierName())
                .build();
    }

    @Override
    public SupplierDto convertModelToDto(Supplier supplier) {
        return SupplierDto.builder()
                .id(supplier.getId())
                .supplierName(supplier.getName())
                .build();
    }
}
