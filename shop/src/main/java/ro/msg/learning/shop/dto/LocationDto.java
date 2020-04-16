package ro.msg.learning.shop.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class LocationDto extends BaseDto{
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
