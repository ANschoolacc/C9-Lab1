package se.C9Lab1;

@FunctionalInterface
public interface IsApplicable<T> {
  public boolean test(T t);
}
