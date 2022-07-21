package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Item type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Items", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byShoppingList", fields = {"shoppinglistID"})
public final class Item implements Model {
  public static final QueryField ID = field("Item", "id");
  public static final QueryField LABEL = field("Item", "label");
  public static final QueryField SHOPPINGLIST_ID = field("Item", "shoppinglistID");
  public static final QueryField QUANTITY = field("Item", "quantity");
  public static final QueryField NOTE = field("Item", "note");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String label;
  private final @ModelField(targetType="ID", isRequired = true) String shoppinglistID;
  private final @ModelField(targetType="Quantity") Quantity quantity;
  private final @ModelField(targetType="String") String note;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getLabel() {
      return label;
  }
  
  public String getShoppinglistId() {
      return shoppinglistID;
  }
  
  public Quantity getQuantity() {
      return quantity;
  }
  
  public String getNote() {
      return note;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Item(String id, String label, String shoppinglistID, Quantity quantity, String note) {
    this.id = id;
    this.label = label;
    this.shoppinglistID = shoppinglistID;
    this.quantity = quantity;
    this.note = note;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Item item = (Item) obj;
      return ObjectsCompat.equals(getId(), item.getId()) &&
              ObjectsCompat.equals(getLabel(), item.getLabel()) &&
              ObjectsCompat.equals(getShoppinglistId(), item.getShoppinglistId()) &&
              ObjectsCompat.equals(getQuantity(), item.getQuantity()) &&
              ObjectsCompat.equals(getNote(), item.getNote()) &&
              ObjectsCompat.equals(getCreatedAt(), item.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), item.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLabel())
      .append(getShoppinglistId())
      .append(getQuantity())
      .append(getNote())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Item {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("label=" + String.valueOf(getLabel()) + ", ")
      .append("shoppinglistID=" + String.valueOf(getShoppinglistId()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("note=" + String.valueOf(getNote()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static LabelStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Item justId(String id) {
    return new Item(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      label,
      shoppinglistID,
      quantity,
      note);
  }
  public interface LabelStep {
    ShoppinglistIdStep label(String label);
  }
  

  public interface ShoppinglistIdStep {
    BuildStep shoppinglistId(String shoppinglistId);
  }
  

  public interface BuildStep {
    Item build();
    BuildStep id(String id);
    BuildStep quantity(Quantity quantity);
    BuildStep note(String note);
  }
  

  public static class Builder implements LabelStep, ShoppinglistIdStep, BuildStep {
    private String id;
    private String label;
    private String shoppinglistID;
    private Quantity quantity;
    private String note;
    @Override
     public Item build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Item(
          id,
          label,
          shoppinglistID,
          quantity,
          note);
    }
    
    @Override
     public ShoppinglistIdStep label(String label) {
        Objects.requireNonNull(label);
        this.label = label;
        return this;
    }
    
    @Override
     public BuildStep shoppinglistId(String shoppinglistId) {
        Objects.requireNonNull(shoppinglistId);
        this.shoppinglistID = shoppinglistId;
        return this;
    }
    
    @Override
     public BuildStep quantity(Quantity quantity) {
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep note(String note) {
        this.note = note;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String label, String shoppinglistId, Quantity quantity, String note) {
      super.id(id);
      super.label(label)
        .shoppinglistId(shoppinglistId)
        .quantity(quantity)
        .note(note);
    }
    
    @Override
     public CopyOfBuilder label(String label) {
      return (CopyOfBuilder) super.label(label);
    }
    
    @Override
     public CopyOfBuilder shoppinglistId(String shoppinglistId) {
      return (CopyOfBuilder) super.shoppinglistId(shoppinglistId);
    }
    
    @Override
     public CopyOfBuilder quantity(Quantity quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder note(String note) {
      return (CopyOfBuilder) super.note(note);
    }
  }
  
}
