package com.example.offer.service;

import com.example.offer.model.BestOffer;
import com.example.offer.model.Product;

import org.springframework.stereotype.Service;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

  public List<BestOffer> processAndFindBestOffer(List<Product> products) {
    if(products==null || products.size()==0){
      return null;
    }

    List<BestOffer> allOffers = new ArrayList<>();

    BestOffer firstCaseBestOffer = getFirstOfferDiscount(products);
    BestOffer secondCaseBestOffer = getSecondOfferDiscount(products);
    BestOffer thirdCaseBestOffer = getThirdOfferDiscount(products);

//    int firstOfferBestDiscount = firstCaseBestOffer.getDiscountedItems().stream().mapToInt(Product::getPrice).sum();
//    int secondOfferBestDiscount = secondCaseBestOffer.getDiscountedItems().stream().mapToInt(Product::getPrice).sum();
//    int thirdOfferBestDiscount = thirdCaseBestOffer.getDiscountedItems().stream().mapToInt(Product::getPrice).sum();
//
//    return firstOfferBestDiscount>secondOfferBestDiscount?(firstOfferBestDiscount>thirdOfferBestDiscount? firstCaseBestOffer:thirdCaseBestOffer):
//           (secondOfferBestDiscount>thirdOfferBestDiscount?secondCaseBestOffer:(thirdCaseBestOffer));

    allOffers.add(firstCaseBestOffer);
    allOffers.add(secondCaseBestOffer);
    allOffers.add(thirdCaseBestOffer);
    return allOffers;

  }

  private BestOffer getThirdOfferDiscount(List<Product> products) {
    List<Product> thisOfferProducts = new ArrayList<>();
    products.forEach(p ->thisOfferProducts.add(new Product(p.getPrice(), false, false)));
    int totalProductCount = thisOfferProducts.size();
    if(totalProductCount<=2){
      return new BestOffer(thisOfferProducts, new ArrayList<>(), "Third Offer Case");
    }
    List<Product> purchasedProducts = new ArrayList<>();
    List<Product> discountedProducts = new ArrayList<>();
    Collections.sort(thisOfferProducts, (a, b) -> (b.getPrice() - a.getPrice()));
    int count =0;
    boolean isPurchasedTurn = true;
    int purchasedProductPrice=Integer.MIN_VALUE;

    while (count<totalProductCount){
      if(totalProductCount%4!=0 && Math.abs(totalProductCount-count)<2){
        isPurchasedTurn=true;
      }
      if(isPurchasedTurn){
        Optional<Product> purchaseProduct = thisOfferProducts.stream().sorted((a, b)-> b.getPrice() - a.getPrice())
            .filter(prdct-> !prdct.isPurchased() && !prdct.isPurchasedInDiscount()).findFirst();
        if(purchaseProduct.isPresent()){
          Product product = purchaseProduct.get();
          purchasedProductPrice = product.getPrice();
          purchasedProducts.add(product);
          product.setPurchased(true);
          count++;
        }
        isPurchasedTurn=false;
      }else{
        int finalPurchasedProductPrice = purchasedProductPrice;
        Optional<Product> discountProductOptional = thisOfferProducts.stream().sorted((a, b)-> b.getPrice() - a.getPrice())
            .filter(prdct-> !prdct.isPurchased() && !prdct.isPurchasedInDiscount() && prdct.getPrice() < finalPurchasedProductPrice).findFirst();
        if(discountProductOptional.isPresent()){
          Product discountProduct = discountProductOptional.get();
          discountedProducts.add(discountProduct);
          discountProduct.setPurchasedInDiscount(true);
          count++;
        }
        isPurchasedTurn=true;
      }
    }

    return new BestOffer(purchasedProducts, discountedProducts, "Third Offer Case");
  }

  private BestOffer getFirstOfferDiscount(List<Product> products) {
    List<Product> thisOfferProducts = new ArrayList<>();
    products.forEach(p ->thisOfferProducts.add(new Product(p.getPrice(), false, false)));
    if(thisOfferProducts.size()<=1){
      return new BestOffer(thisOfferProducts, new ArrayList<>(), "First Offer Case");
    }

    List<Product> purchasedProducts = new ArrayList<>();
    List<Product> discountedProducts = new ArrayList<>();
    Collections.sort(thisOfferProducts, (a, b) -> (b.getPrice()-a.getPrice()));

    for (int i = 0; i < thisOfferProducts.size();) {
      Product purchasedProduct = thisOfferProducts.get(i);
      purchasedProducts.add(purchasedProduct);
      purchasedProduct.setPurchased(true);
      i++;
      if( i < thisOfferProducts.size()){
        Product discountedProduct = thisOfferProducts.get(i);
        discountedProducts.add(discountedProduct);
        discountedProduct.setPurchasedInDiscount(true);
        i++;
      }
    }
    return new BestOffer(purchasedProducts, discountedProducts, "First Offer Case");
  }

  private BestOffer getSecondOfferDiscount(List<Product> products) {
    List<Product> thisOfferProducts = new ArrayList<>();
    products.forEach(p ->thisOfferProducts.add(new Product(p.getPrice(), false, false)));

    if(thisOfferProducts.size()<=1){
      return new BestOffer(thisOfferProducts, new ArrayList<>(), "Second Offer Case");
    }

    List<Product> purchasedProducts = new ArrayList<>();
    List<Product> discountedProducts = new ArrayList<>();


    int countOfProductTaken=0;
    int totalProductsCount = thisOfferProducts.size();
    boolean purchaseTurn = true;
    int purchasedProductPrice=Integer.MIN_VALUE;
    while (countOfProductTaken<totalProductsCount){
      if(purchaseTurn){
        Optional<Product> purchaseProduct = thisOfferProducts.stream().sorted((a, b)-> b.getPrice() - a.getPrice())
            .filter(prdct-> !prdct.isPurchased() && !prdct.isPurchasedInDiscount()).findFirst();
        if(purchaseProduct.isPresent()){
          purchasedProducts.add(purchaseProduct.get());
          purchaseProduct.get().setPurchased(true);
          purchasedProductPrice = purchaseProduct.get().getPrice();
          countOfProductTaken++;
        }
        purchaseTurn=false;
      }else{
        int finalPurchasedProductPrice = purchasedProductPrice;
        Optional<Product> discountedProduct = thisOfferProducts.stream().sorted((a, b)-> b.getPrice() - a.getPrice())
            .filter(prdct-> !prdct.isPurchased() && !prdct.isPurchasedInDiscount() && prdct.getPrice() < finalPurchasedProductPrice).findFirst();

        if(discountedProduct.isPresent()){
          discountedProducts.add(discountedProduct.get());
          discountedProduct.get().setPurchasedInDiscount(true);
          countOfProductTaken++;
        }
        purchaseTurn=true;
      }
    }
    return new BestOffer(purchasedProducts, discountedProducts, "Second Offer Case");
  }

}
