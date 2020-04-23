package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CustomerDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
}
