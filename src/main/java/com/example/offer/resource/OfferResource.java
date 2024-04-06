package com.example.offer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.offer.model.BestOffer;
import com.example.offer.model.Product;
import com.example.offer.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferResource {

  @Autowired
  private OfferService offerService;

  @PostMapping("/offer")
  public List<BestOffer> processAndFindBestOffer(@RequestBody List<Product> products){
    return offerService.processAndFindBestOffer(products);
  }

}
