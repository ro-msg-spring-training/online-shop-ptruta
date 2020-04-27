package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class SupplierDto implements Serializable {

    private Integer id;

    private String supplierName;
}
