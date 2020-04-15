package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Revenue {
    @Id
    private Integer id;
    @ManyToOne
    private Location location;
    private LocalDate date;
    private BigDecimal sum;
}