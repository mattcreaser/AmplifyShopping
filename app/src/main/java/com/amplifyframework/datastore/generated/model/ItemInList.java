package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
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

/** This is an auto generated class representing the ItemInList type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ItemInLists", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class ItemInList implements Model {
  public static final QueryField ID = field("ItemInList", "id");
  public static final QueryField ITEM_IN_LIST_ITEM_ID = field("ItemInList", "itemInListItemId");
  public static final QueryField ITEM_IN_LIST_LIST_ID = field("ItemInList", "itemInListListId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Item") @HasOne(associatedWith = "id", type = Item.class) Item Item = null;
  private final @ModelField(targetType="ShoppingList") @HasOne(associatedWith = "id", type = ShoppingList.class) ShoppingList List = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String itemInListItemId;
  private final @ModelField(targetType="ID") String itemInListListId;
  public String getId() {
      return id;
  }
  
  public Item getItem() {
      return Item;
  }
  
  public ShoppingList getList() {
      return List;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getItemInListItemId() {
      return itemInListItemId;
  }
  
  public String getItemInListListId() {
      return itemInListListId;
  }
  
  private ItemInList(String id, String itemInListItemId, String itemInListListId) {
    this.id = id;
    this.itemInListItemId = itemInListItemId;
    this.itemInListListId = itemInListListId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ItemInList itemInList = (ItemInList) obj;
      return ObjectsCompat.equals(getId(), itemInList.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), itemInList.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), itemInList.getUpdatedAt()) &&
              ObjectsCompat.equals(getItemInListItemId(), itemInList.getItemInListItemId()) &&
              ObjectsCompat.equals(getItemInListListId(), itemInList.getItemInListListId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getItemInListItemId())
      .append(getItemInListListId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ItemInList {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("itemInListItemId=" + String.valueOf(getItemInListItemId()) + ", ")
      .append("itemInListListId=" + String.valueOf(getItemInListListId()))
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
  public static ItemInList justId(String id) {
    return new ItemInList(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      itemInListItemId,
      itemInListListId);
  }
  public interface BuildStep {
    ItemInList build();
    BuildStep id(String id);
    BuildStep itemInListItemId(String itemInListItemId);
    BuildStep itemInListListId(String itemInListListId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String itemInListItemId;
    private String itemInListListId;
    @Override
     public ItemInList build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ItemInList(
          id,
          itemInListItemId,
          itemInListListId);
    }
    
    @Override
     public BuildStep itemInListItemId(String itemInListItemId) {
        this.itemInListItemId = itemInListItemId;
        return this;
    }
    
    @Override
     public BuildStep itemInListListId(String itemInListListId) {
        this.itemInListListId = itemInListListId;
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
    private CopyOfBuilder(String id, String itemInListItemId, String itemInListListId) {
      super.id(id);
      super.itemInListItemId(itemInListItemId)
        .itemInListListId(itemInListListId);
    }
    
    @Override
     public CopyOfBuilder itemInListItemId(String itemInListItemId) {
      return (CopyOfBuilder) super.itemInListItemId(itemInListItemId);
    }
    
    @Override
     public CopyOfBuilder itemInListListId(String itemInListListId) {
      return (CopyOfBuilder) super.itemInListListId(itemInListListId);
    }
  }
  
}
