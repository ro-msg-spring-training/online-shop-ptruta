package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class ProductCategory {
@Id
private Integer id;
private String name;
private String description;
}