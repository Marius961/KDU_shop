package ua.shop.kdu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderedItem> orderedItems;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @NotBlank
    @Size(max = 80)
    private String deliveryCity;

    @NotBlank
    @Size(max = 64)
    private String deliveryStreet;

    @NotBlank
    @Size(max = 16)
    private String deliveryBuildingNumber;

    @NotNull
    private int deliveryApartmentNumber;

    @Size(max = 512)
    private String comment;

    private double totalPrice;

    private boolean isConfirmed;

    private boolean isShippedOut;

    private boolean isDelivered;

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

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryBuildingNumber() {
        return deliveryBuildingNumber;
    }

    public void setDeliveryBuildingNumber(String deliveryBuildingNumber) {
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isShippedOut() {
        return isShippedOut;
    }

    public void setShippedOut(boolean shippedOut) {
        isShippedOut = shippedOut;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
