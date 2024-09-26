package se.C9Lab1.decorators;

import se.C9Lab1.entities.Product;

@FunctionalInterface
public interface IsApplicable {
    boolean test(Product product);
}
