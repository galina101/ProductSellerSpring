package org.example.Controller;

import java.util.List;
import java.util.Optional;
import org.example.Entity.Product;
import org.example.Entity.Seller;
import org.example.Exceptions.ProductNotFoundException;
import org.example.Exceptions.SellerFormatException;
import org.example.Repository.SellerRepository;
import org.example.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  SellerRepository sellerRepository;

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

  @PostMapping("/seller/{sellerId}/product")
  public ResponseEntity<Product> addProduct(@PathVariable long sellerId,
      @RequestBody Product p) throws Exception {
      // Fetch the Seller from the database using the sellerId
      Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
      if (optionalSeller.isPresent()) {
        Seller seller = optionalSeller.get();
        // Set the seller to the product
        p.setSeller(seller);
        Product product = productService.saveProduct(sellerId, p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
      } else {
        throw new SellerFormatException("Seller not found");
      }
  }

  @GetMapping(value = "/product/{productId}")
  public ResponseEntity<Product> getProductById(@PathVariable long productId) {

    try {
      Product p = productService.getById(productId);
      return new ResponseEntity<>(p, HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/product/{productId}")
  public ResponseEntity<Product> deleteProductById(@PathVariable long productId) {
    try {
      productService.deleteProductById(productId);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
  }

  @PutMapping(value = "seller/{sellerId}/product/{productId}")
  public ResponseEntity<Product> updateProductById(@PathVariable long sellerId,
      @PathVariable long productId,
      @RequestBody Product p) throws Exception {

    Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
    if (optionalSeller.isPresent()) {
      Seller seller = optionalSeller.get();
      // Set the seller to the product
      p.setSeller(seller);
      Product product = productService.updateProductById(sellerId, productId, p);
      return new ResponseEntity<>(product, HttpStatus.CREATED);
    } else {
      throw new SellerFormatException("Seller not found");
    }
  }

}


