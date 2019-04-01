package org.dhbw.mosbach.ai.partyplanner.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
/**@author Pascal Röcker
 * Model for a party including name, guests and a whishlist
 */
@Entity
@Table(name = "party")
@XmlRootElement
public class Party {
    private long id;
    private String name;
    private List<Guest> guests = Lists.newArrayList();
    private List<Item> whishlist = Lists.newArrayList();

    @Column(nullable = false, length = 256, unique = true)
    @XmlAttribute(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlElement(name = "guest")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @XmlElement(name = "item")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<Item> getWhishlist() {
        return whishlist;
    }

    public void setWhishlist(List<Item> whishlist) {
        this.whishlist = whishlist;
    }
}
