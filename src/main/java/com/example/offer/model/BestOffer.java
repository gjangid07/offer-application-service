package com.example.offer.model;

import java.util.List;

public class BestOffer{
  String offerCaseName;
  List<Product> payableItems;
  List<Product> discountedItems;


  public String getOfferCaseName() {
    return offerCaseName;
  }

  public void setOfferCaseName(String offerCaseName) {
    this.offerCaseName = offerCaseName;
  }

  public BestOffer(List<Product> payableItems, List<Product> discountedItems, String offerCaseName) {
    this.payableItems = payableItems;
    this.discountedItems = discountedItems;
    this.offerCaseName = offerCaseName;
  }

  public List<Product> getPayableItems() {
    return payableItems;
  }

  public void setPayableItems(List<Product> payableItems) {
    this.payableItems = payableItems;
  }

  public List<Product> getDiscountedItems() {
    return discountedItems;
  }

  public void setDiscountedItems(List<Product> discountedItems) {
    this.discountedItems = discountedItems;
  }

  @Override
  public String toString() {
    return "BestOffer{" +
           "offerCaseName='" + offerCaseName + '\'' +
           ", payableItems=" + payableItems +
           ", discountedItems=" + discountedItems +
           '}';
  }
}
