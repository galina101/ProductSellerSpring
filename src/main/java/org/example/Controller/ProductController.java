package org.example.Controller;

import java.util.List;
import org.example.Entity.Product;
import org.example.Exceptions.ProductNotFoundException;
import org.example.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = "/product", params = "name")
  public ResponseEntity<List<Product>> getAllProductsByName(
      @RequestParam String name) {
    List<Product> products;
    if (name == null) {
      products = productService.getAllProducts();
    } else {
      products = productService.getAllProductsByName(name);
    }
    return new ResponseEntity<>(products, HttpStatus.OK);
  }


  @GetMapping(value = "/product")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products;
    products = productService.getAllProducts();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @PostMapping("/seller/{id}/product")
  public ResponseEntity<Product> addProduct(@PathVariable long id,
      @RequestBody Product p) throws Exception {
    Product product = productService.saveProduct(id, p);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @GetMapping(value = "/product/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable long id) {

    try {
      Product p = productService.getById(id);
      return new ResponseEntity<>(p, HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/product/{id}")
  public ResponseEntity<Product> deleteProductById(@PathVariable long id) {
    try {
      productService.deleteProductById(id);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
  }

  @PutMapping(value = "/product/{id}")
  public ResponseEntity<Product> updateProductById(@PathVariable long id, @RequestBody Product p) {
    try {
      Product product = productService.updateProductById(id, p);
      return new ResponseEntity<>(product, HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

}


