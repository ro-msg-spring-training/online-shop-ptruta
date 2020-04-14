package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Stock {
    @Id
    @GeneratedValue
    private Integer stock_id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
    private Integer quantity;
}
