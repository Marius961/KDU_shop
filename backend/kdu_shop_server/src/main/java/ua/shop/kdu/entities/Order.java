package ua.shop.kdu.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderedItem> orderedItems;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @NotBlank
    private String deliveryCity;

    @NotBlank
    private String deliveryStreen;

    @NotNull
    private int deliveryBuildingNumber;

    @NotNull
    private int deliveryApartmentNumber;

    @Size(max = 512)
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryStreen() {
        return deliveryStreen;
    }

    public void setDeliveryStreen(String deliveryStreen) {
        this.deliveryStreen = deliveryStreen;
    }

    public int getDeliveryBuildingNumber() {
        return deliveryBuildingNumber;
    }

    public void setDeliveryBuildingNumber(int deliveryBuildingNumber) {
        this.deliveryBuildingNumber = deliveryBuildingNumber;
    }

    public int getDeliveryApartmentNumber() {
        return deliveryApartmentNumber;
    }

    public void setDeliveryApartmentNumber(int deliveryApartmentNumber) {
        this.deliveryApartmentNumber = deliveryApartmentNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
