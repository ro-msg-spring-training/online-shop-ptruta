package ro.msg.learning.shop.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class CustomerDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
}
