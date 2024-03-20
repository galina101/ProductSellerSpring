package org.example.Repository;

import java.util.List;
import org.example.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>{
  List<Product> findByName(String name);

  @Query("from Product where name=:name")
  List<Product> findByName2(@Param ("name") String name);

}
