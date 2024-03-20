package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;
  public String name;
  public BigDecimal price;
  //public Integer sellerId;
  //The `@JsonIgnore` annotation in the provided code is used to prevent
  // the specified property (in this case, `artist`) from being included
  // in the JSON serialization of an object of the `Painting` class.
  //
  //In other words, when an instance of `Painting` is converted to JSON format
  // (for example, to send as a response from a REST API), the `artist` field
  // will not be included in the resulting JSON. This can be useful for
  // preventing infinite recursion issues when dealing with bidirectional relationships
  // in entities, or for excluding sensitive or unnecessary data from the output.
  @JsonIgnore

  @ManyToOne
  //In this case, it helps to avoid a potential infinite loop during serialization
  // where a Painting references an Artist, which in turn references
  // a list of Painting objects, and so on.
  @JsonIgnoreProperties("products")
  public Seller seller;

//Initial shape of Product class
//  private int id;
//  private String name;
//  private int price;
//  private int seller;

}
