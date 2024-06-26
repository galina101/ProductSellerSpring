package org.example.Controller;

import java.util.List;
import org.example.Entity.Seller;
import org.example.Exceptions.SellerFormatException;
import org.example.Service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

  SellerService sellerService;

  public SellerController(SellerService sellerService){
    this.sellerService = sellerService;
  }

  @GetMapping("/seller")
  public ResponseEntity<List<Seller>> getAllSellers(){
    List<Seller> sellers = sellerService.getAllSellers();
    return new ResponseEntity<>(sellers, HttpStatus.OK);
  }

  @PostMapping("/seller")
  public ResponseEntity<Seller> addSeller(@RequestBody Seller s)
      throws SellerFormatException {
    Seller seller = sellerService.saveSeller(s);
    return new ResponseEntity<>(seller, HttpStatus.CREATED);
  }

//  @RequestMapping("/seller")
//  public ResponseEntity<String> defaultMethod() {
//    return new ResponseEntity<>("Operation unavailable for this URL",
//        HttpStatus.NOT_FOUND);
//  }
}
