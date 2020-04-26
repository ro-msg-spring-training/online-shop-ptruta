package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class LocationDto implements Serializable {

    private Integer id;

    private String name;

    private String country;

    private String city;

    private String county;

    private String streetAddress;
}
