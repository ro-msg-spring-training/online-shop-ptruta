package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "location", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
@Builder
public class Location {
    @Id
    private Integer id;
    private String name;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreetAddress;
//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<Stock> stocks = new ArrayList<>();
//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<Orders> orders = new ArrayList<>();
//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<Revenue> revenues = new ArrayList<>();
}
