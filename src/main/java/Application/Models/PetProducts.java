package Application.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pet_products")
public class PetProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price; // Using Double to store price values

    @Column(name = "available_stock", nullable = false)
    private Integer availableStock; // Number of items in stock

    @Column(name = "category", nullable = false)
    private String category; // Category of the product (e.g., food, toys, grooming)

    @Column(name = "photo", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] photo; // Product photo

    @Column(name = "added_date", nullable = false, columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate; // Automatically store the date the product was added

    @Column(name = "vendor_name", nullable = false)
    private String vendorName; // Vendor or seller name

    @Column(name = "vendor_phone", nullable = false)
    private String vendorPhone; // Vendor's contact number

    // Default constructor
    public PetProducts() {
        this.addedDate = new Date(); // Set the current date and time when the product is added
    }

    // Constructor with parameters
    public PetProducts(Long id, String productName, String description, Double price, Integer availableStock,
                       String category, byte[] photo, Date addedDate, String vendorName, String vendorPhone) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.availableStock = availableStock;
        this.category = category;
        this.photo = photo;
        this.addedDate = addedDate;
        this.vendorName = vendorName;
        this.vendorPhone = vendorPhone;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetProducts)) return false;
        PetProducts that = (PetProducts) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Override toString
    @Override
    public String toString() {
        return "PetProducts{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availableStock=" + availableStock +
                ", category='" + category + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", vendorPhone='" + vendorPhone + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }
}