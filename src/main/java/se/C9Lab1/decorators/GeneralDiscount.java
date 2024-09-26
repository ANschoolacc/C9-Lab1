package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class GeneralDiscount implements Discount {
  Discount nextDiscount;
  String description;
  IsApplicable isApplicable;
  CalculateDiscount calculation;

  public GeneralDiscount(String description , IsApplicable isApplicable, CalculateDiscount calculateDiscount, Discount nextDiscount) {
    this.nextDiscount = nextDiscount;
    this.description = description;
    this.isApplicable = isApplicable;
    this.calculation = calculateDiscount;
  }

  @Override
  public List<Double> apply(ShoppingCart shoppingCart) {
    List<Double> discounts = new ArrayList<>();
    if(isApplicable.test(shoppingCart)){
      discounts.add(calculation.calculateDiscount(shoppingCart));
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
    if(isApplicable.test(shoppingCart)){
      descriptions.add(description);
    }

    List<String> nextDescription = nextDiscount.getDescription(shoppingCart);

    if(!descriptions.isEmpty() && !nextDescription.isEmpty()){
      descriptions.addAll(nextDescription);
    }else if(!nextDescription.isEmpty()){
      descriptions.addAll(nextDescription);
    }

    return descriptions.stream().toList();
  }
}
