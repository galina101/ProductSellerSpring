package org.example.Entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Seller {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;
  public String name;
  @OneToMany(mappedBy="seller")
  //specify the foreign key column
  //@JoinColumn(name="seller_fk")
  public List<Product> products;
  //if a Product represents a seller, and each seller can have multiple products,
// then this product field could be used to hold all products associated
// with a particular seller.
}
