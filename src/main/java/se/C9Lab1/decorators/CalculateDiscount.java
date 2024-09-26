package se.C9Lab1.decorators;

import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

@FunctionalInterface
public interface CalculateDiscount {
    double calculateDiscount(ShoppingCart shoppingCart);
}
