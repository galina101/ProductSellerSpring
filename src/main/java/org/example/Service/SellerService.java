package org.example.Service;

import java.util.List;
import org.example.Entity.Seller;
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

  public Seller saveSeller(Seller s){
    return sellerRepository.save(s);
  }

}
