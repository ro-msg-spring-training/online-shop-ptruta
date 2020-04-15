package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

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
}
