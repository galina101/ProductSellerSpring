package org.example.Service;

import java.util.List;
import org.example.Entity.Seller;
import org.example.Exceptions.ProductNotFoundException;
import org.example.Exceptions.SellerFormatException;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

  SellerRepository sellerRepository;

  @Autowired
  public SellerService(SellerRepository sellerRepository){
    this.sellerRepository = sellerRepository;
  }

  public List<Seller> getAllSellers(){
    return sellerRepository.findAll();
  }

  public Seller saveSeller(Seller s) throws SellerFormatException {
    //check if the seller name is null or empty
    if (s.getName() == null || s.getName().trim().isEmpty()) {
      throw new SellerFormatException("Seller name cannot be null or empty");
    }

    //check if seller name already exists in seller table
    if(sellerRepository.existsByName(s.getName())){
      throw new SellerFormatException("Seller name already exists");
    }

    return sellerRepository.save(s);
  }

}
