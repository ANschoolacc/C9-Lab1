package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class BaseDiscount implements Discount {
  protected Discount nextDiscount;

  BaseDiscount(Discount nextDiscount) {
    this.nextDiscount = nextDiscount;
  }

//  @Override
//  public double apply(ShoppingCart shoppingCart) {
//    double discount = 0;
//      if(isApplicable(shoppingCart)){
//        discount = calculateDiscount(shoppingCart);
//      }
//      discount += nextDiscount.apply(shoppingCart);
//      return discount;
//  }

  @Override
  public List<Double> apply(ShoppingCart shoppingCart) {
    List<Double> discounts = new ArrayList<>();
    if(isApplicable(shoppingCart)){
      discounts.add(calculateDiscount(shoppingCart));
    }

    if (!discounts.isEmpty() && !nextDiscount.apply(shoppingCart).isEmpty()) {
      discounts.addAll(nextDiscount.apply(shoppingCart));
    }else if (!nextDiscount.apply(shoppingCart).isEmpty()) {
      discounts.addAll(nextDiscount.apply(shoppingCart));
    }

    return discounts.stream().toList();
  }

  @Override
  public List<String> getDescription(ShoppingCart shoppingCart) {
    List<String> descriptions = new ArrayList<>();
    if(isApplicable(shoppingCart)){
      descriptions.add(getOwnDescription());
    }

    List<String> nextDescription = nextDiscount.getDescription(shoppingCart);

    if(!descriptions.isEmpty() && !nextDescription.isEmpty()){
      descriptions.addAll(nextDescription);
    }else if(!nextDescription.isEmpty()){
      descriptions.addAll(nextDescription);
    }

    return descriptions.stream().toList();
  }

  protected abstract boolean isApplicable(ShoppingCart shoppingCart);
  protected abstract double calculateDiscount(ShoppingCart shoppingCart);
  protected abstract String getOwnDescription();
}
