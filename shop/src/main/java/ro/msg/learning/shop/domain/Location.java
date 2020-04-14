package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Location {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreetAddress;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Revenue> revenues = new ArrayList<>();
}
