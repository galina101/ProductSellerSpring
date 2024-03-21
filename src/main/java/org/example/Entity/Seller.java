package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Seller {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;
  public String name;

  @OneToMany(mappedBy = "seller")
  @JsonBackReference
  //Need to add, remove mapped by
  // @OneToMany
   //@JoinColumn(name="seller_id")

  //specify the foreign key column
  //@JoinColumn(name="seller_fk")
  public List<Product> products;
  //if a Product represents a seller, and each seller can have multiple products,
// then this product field could be used to hold all products associated
// with a particular seller.
  /* In a one-to-many relationship in JPA, the "one" side
  (in this case, the Seller entity) often maintains a collection of the "many" side
  (the Product entities). This is done to represent the relationship in the
  object-oriented domain model, where a Seller can have multiple Products.
  The List<Product> products in the Seller entity represents all the
  Products associated with a particular Seller. This is useful when you want to
  retrieve all the Products for a Seller directly from the Seller object,
  without having to write a separate query.  For example, if you have a Seller object,
   you can get all its Products by calling seller.getProducts(),
   which will return a list of Product objects. This is possible because of the
   List<Product> products field in the Seller entity.  It's important to note that
   this is not required for the one-to-many relationship to work at the database level.
    The relationship is still maintained through the foreign key in the Product table.
    The List<Product> products field is mainly for convenience at the application level.
  * */
}
