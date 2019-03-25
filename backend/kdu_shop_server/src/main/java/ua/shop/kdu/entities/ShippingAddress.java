package ua.shop.kdu.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "address")
    private Order order;

    @NotBlank
    @Size(min = 5, max = 64)
    private String recipientFullName;

    @NotBlank
    @Size(min = 2, max = 64)
    private String city;

    @NotBlank
    @Size(min = 2, max = 80)
    private String region;

    @NotBlank
    @Size(min = 2,max = 64)
    private String street;

    @NotBlank
    @Size(min = 1,max = 16)
    private String buildingNumber;

    @NotNull
    private int apartmentNumber;

    @Size(max = 256)
    private String postOfficeNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostOfficeNumber() {
        return postOfficeNumber;
    }

    public void setPostOfficeNumber(String postOfficeNumber) {
        this.postOfficeNumber = postOfficeNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

