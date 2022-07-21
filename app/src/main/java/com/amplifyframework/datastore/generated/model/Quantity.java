package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the Quantity type in your schema. */
public final class Quantity {
  private final Double amount;
  private final QuantityUnit unit;
  public Double getAmount() {
      return amount;
  }
  
  public QuantityUnit getUnit() {
      return unit;
  }
  
  private Quantity(Double amount, QuantityUnit unit) {
    this.amount = amount;
    this.unit = unit;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Quantity quantity = (Quantity) obj;
      return ObjectsCompat.equals(getAmount(), quantity.getAmount()) &&
              ObjectsCompat.equals(getUnit(), quantity.getUnit());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getAmount())
      .append(getUnit())
      .toString()
      .hashCode();
  }
  
  public static AmountStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(amount,
      unit);
  }
  public interface AmountStep {
    BuildStep amount(Double amount);
  }
  

  public interface BuildStep {
    Quantity build();
    BuildStep unit(QuantityUnit unit);
  }
  

  public static class Builder implements AmountStep, BuildStep {
    private Double amount;
    private QuantityUnit unit;
    @Override
     public Quantity build() {
        
        return new Quantity(
          amount,
          unit);
    }
    
    @Override
     public BuildStep amount(Double amount) {
        Objects.requireNonNull(amount);
        this.amount = amount;
        return this;
    }
    
    @Override
     public BuildStep unit(QuantityUnit unit) {
        this.unit = unit;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(Double amount, QuantityUnit unit) {
      super.amount(amount)
        .unit(unit);
    }
    
    @Override
     public CopyOfBuilder amount(Double amount) {
      return (CopyOfBuilder) super.amount(amount);
    }
    
    @Override
     public CopyOfBuilder unit(QuantityUnit unit) {
      return (CopyOfBuilder) super.unit(unit);
    }
  }
  
}
