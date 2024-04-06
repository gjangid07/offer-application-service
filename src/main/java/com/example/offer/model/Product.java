package com.example.offer.model;

public class Product{
  int price;
  boolean isPurchased;
  boolean isPurchasedInDiscount;

  public Product(int price, boolean isPurchased, boolean isPurchasedInDiscount) {
    this.price = price;
    this.isPurchased = isPurchased;
    this.isPurchasedInDiscount = isPurchasedInDiscount;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public boolean isPurchased() {
    return isPurchased;
  }

  public void setPurchased(boolean purchased) {
    isPurchased = purchased;
  }

  public boolean isPurchasedInDiscount() {
    return isPurchasedInDiscount;
  }

  public void setPurchasedInDiscount(boolean purchasedInDiscount) {
    isPurchasedInDiscount = purchasedInDiscount;
  }

  @Override
  public String toString() {
    return "Product{" +
           "price=" + price +
           '}';
  }
}
