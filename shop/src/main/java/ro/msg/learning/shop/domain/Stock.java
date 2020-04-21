package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "stock", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @EmbeddedId
    private StockKey id;
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "location_id")
    private Location location;
    private Integer quantity;
}
