package se.C9Lab1.decorators;

import org.junit.jupiter.api.BeforeEach;
import se.C9Lab1.Product;

public class BaseDiscountTest {
@BeforeEach
  void setUp() {
  Product mockProduct1 = new Product("Mjölk", 20, 2);
  Product mockProduct2 = new Product("Smör", 50, 5);
  Product mockProduct3 = new Product("mjölk", 100, 5);
  Product mockProduct4 = new Product("ost", 60, 1);
}

}
