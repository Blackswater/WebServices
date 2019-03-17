package org.dhbw.mosbach.ai.partyplanner.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@Entity
@Table(name = "guests")
public class Guest {
    private long id;
    private Party party;

    private String code;
    private String name;
    private List<Item> items = Lists.newArrayList();

    @Id
    @GeneratedValue
    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(nullable = false, length = 256, unique = true)
    @XmlAttribute(required = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(nullable = false, length = 256, unique = true)
    @XmlAttribute(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "items")
    // Specify attribute in Review that holds the foreign key
    @OneToMany(mappedBy = "Guest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    @XmlTransient
    @ManyToOne
    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
