package fi.metropolia.ilkkas.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "order_date")
    private LocalDateTime orderDate;
    
    @Column (name = "delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "shipping_address_id")
    private CustomerAddresses shippingAddress;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonBackReference
    private List<OrderItems> orderItems = new ArrayList<>();

    public Orders() {
        // Default constructor
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }

    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public LocalDateTime getDeliveryDate() { return deliveryDate; }

    public void setDeliveryDate(LocalDateTime deliveryDate) { 
        this.deliveryDate = deliveryDate; }

    public Customers getCustomer() { return customer; }
    
    public void setCustomer(Customers customer) { this.customer = customer; }

    public List<OrderItems> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItems> orderItems) { this.orderItems = orderItems; }

    public CustomerAddresses getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(CustomerAddresses shippingAddress) { this.shippingAddress = shippingAddress;
    }
}
