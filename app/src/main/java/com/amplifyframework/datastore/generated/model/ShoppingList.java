package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the ShoppingList type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ShoppingLists", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class ShoppingList implements Model {
  public static final QueryField ID = field("ShoppingList", "id");
  public static final QueryField LABEL = field("ShoppingList", "label");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String label;
  private final @ModelField(targetType="Item") @HasMany(associatedWith = "shoppinglistID", type = Item.class) List<Item> Items = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getLabel() {
      return label;
  }
  
  public List<Item> getItems() {
      return Items;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ShoppingList(String id, String label) {
    this.id = id;
    this.label = label;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ShoppingList shoppingList = (ShoppingList) obj;
      return ObjectsCompat.equals(getId(), shoppingList.getId()) &&
              ObjectsCompat.equals(getLabel(), shoppingList.getLabel()) &&
              ObjectsCompat.equals(getCreatedAt(), shoppingList.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), shoppingList.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLabel())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ShoppingList {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("label=" + String.valueOf(getLabel()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static ShoppingList justId(String id) {
    return new ShoppingList(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      label);
  }
  public interface BuildStep {
    ShoppingList build();
    BuildStep id(String id);
    BuildStep label(String label);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String label;
    @Override
     public ShoppingList build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ShoppingList(
          id,
          label);
    }
    
    @Override
     public BuildStep label(String label) {
        this.label = label;
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
    private CopyOfBuilder(String id, String label) {
      super.id(id);
      super.label(label);
    }
    
    @Override
     public CopyOfBuilder label(String label) {
      return (CopyOfBuilder) super.label(label);
    }
  }
  
}
