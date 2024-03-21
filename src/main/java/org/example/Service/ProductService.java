package org.example.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.example.Entity.Product;
import org.example.Entity.Seller;
import org.example.Exceptions.ProductNotFoundException;
import org.example.Repository.ProductRepository;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  ProductRepository productRepository;
  SellerRepository sellerRepository;

  @Autowired
  public ProductService(ProductRepository productRepository,
      SellerRepository sellerRepository) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product saveProduct(Long id, Product p) throws ProductNotFoundException {
    //Check if product name is not null
    //trim() is called on the product name,
    // which removes any leading and trailing whitespace characters.
    if (p.getName() == null || p.getName().trim().isEmpty()) {
      throw new ProductNotFoundException("Product name cannot be null or empty");
    }

    //price cannot be null
    if (p.getPrice() == null) {
      throw new ProductNotFoundException("Product price cannot be null");
    }
    //price must be over zero
    if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new ProductNotFoundException("Product price must be more than zero");
    }

    //check if seller exists
    Optional<Seller> optional = sellerRepository.findById(id);
    Seller s;
    if (optional.isEmpty()) {
      throw new ProductNotFoundException("No such seller...");
    } else {
      s = optional.get();
    }
    Product savedProduct = productRepository.save(p);
    s.getProducts().add(savedProduct);
    sellerRepository.save(s);
    return savedProduct;
  }

  public List<Product> getAllProductsByName(String name) {
    return productRepository.findByName2(name);
  }

  public Product getById(long id) throws ProductNotFoundException {
    Optional<Product> p = productRepository.findById(id);
    if (p.isEmpty()) {
      throw new ProductNotFoundException("no such product... ");
    } else {
      return p.get();
    }
  }

  public void deleteProductById(long id) throws ProductNotFoundException {
    Optional<Product> p = productRepository.findById(id);
    if (p.isEmpty()) {
      throw new ProductNotFoundException("No such product... ");
    } else {
      productRepository.deleteById(id);
    }
  }

  //UNFINISHED
  public Product updateProductById(long id, Product p)
      throws ProductNotFoundException {

    //Check if product name is not null
    //trim() is called on the product name,
    // which removes any leading and trailing whitespace characters.
    if (p.getName() == null || p.getName().trim().isEmpty()) {
      throw new ProductNotFoundException("Product name cannot be null or empty");
    }

    //price cannot be null
    if (p.getPrice() == null) {
      throw new ProductNotFoundException("Product price cannot be null");
    }
    //price must be over zero
    if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new ProductNotFoundException("Product price must be more than zero");
    }


    //check if seller exists
    Optional<Seller> optional = sellerRepository.findById(id);
    Seller s;
    if (optional.isEmpty()) {
      throw new ProductNotFoundException("No such seller...");
    } else {
      s = optional.get();
    }
    //Check if product exists
    Optional<Product> product = productRepository.findById(id);
    if (product.isEmpty()) {
      throw new ProductNotFoundException("No such product... ");
    } else {
      p.setId(id);
      productRepository.save(p);
    }
    return p;
  }
}
