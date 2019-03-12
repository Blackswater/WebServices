package org.dhbw.mosbach.ai.partyplanner.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
@Entity
@Table(name = "Ingredients")
public class Ingredient {
    private long id;
    private String name;
    private int  amount;
    private String measure;

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
    @Column(nullable = false, length = 256)
    @XmlAttribute(required = true)
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
