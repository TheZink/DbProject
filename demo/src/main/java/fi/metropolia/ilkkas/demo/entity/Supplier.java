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
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "contact_name")
    private String contactName;
    @Column (name="phone")
    private String phone;
    @Column (name="email")
    private String email;

    @OneToMany(mappedBy = "supplier",
            fetch = FetchType.LAZY, 
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<Products> products = new ArrayList<>();

    @OneToOne(mappedBy = "supplier", 
            cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    private SupplierAddresses supplierAddresses;

    public Supplier() {
        // Default constructor
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public SupplierAddresses getSupplierAddresses() { return supplierAddresses; }
    public void setSupplierAddresses(SupplierAddresses supplierAddresses) { this.supplierAddresses = supplierAddresses; }
}
