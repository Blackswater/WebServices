package org.dhbw.mosbach.ai.partyplanner.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**@author Pascal Röcker
 * Model for a item consting of name, type, amount, ingredients and a recipe
 * nothing found =null
 */
@Entity
@Table(name = "Item")
public class Item {
    private long id;
    private String name;
    private ItemType type;
    private Integer amount=1;
    private List<Ingredient> ingredients = Lists.newArrayList();
    private String recipe;

    public Item(){
        super();
    }
    public Item(String name,ItemType type,int amount){
        this();
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public Item(Item current) {
       this();
        id=current.id;
        name=current.name;
        type=current.type;
        ingredients=current.ingredients;
        recipe=current.recipe;
        amount=current.amount;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Id
    @GeneratedValue
    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(nullable = false, length = 256)
    @XmlAttribute(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Enumerated(EnumType.ORDINAL)
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
    @XmlElement(name = "ingredient")
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
