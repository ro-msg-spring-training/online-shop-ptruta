package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
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
//    private List<Order> orders = new ArrayList<>();
//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<Revenue> revenues = new ArrayList<>();
}
