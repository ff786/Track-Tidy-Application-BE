package lk.sliit.itpm.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "home_inventory")
public class inventory {
    @Id
    private String id;
    private String userId;
    private String productName;
    private String productId;
    private int quantity;
    private double productValue;
    private Date purchaseDate;
    private int warrantyPeriod;
    private String productCategory;
    private String productImage;

    public inventory() {}

    public inventory(String userId, String productName, String productId, int quantity, double productValue, Date purchaseDate, int warrantyPeriod, String productCategory, String productImage) {
        this.userId = userId;
        this.productName = productName;
        this.productId = productId;
        this.quantity = quantity;
        this.productValue = productValue;
        this.purchaseDate = purchaseDate;
        this.warrantyPeriod = warrantyPeriod;
        this.productCategory = productCategory;
        this.productImage = productImage;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getProductValue() { return productValue; }
    public void setProductValue(double productValue) { this.productValue = productValue; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }

    public int getWarrantyPeriod() { return warrantyPeriod; }
    public void setWarrantyPeriod(int warrantyPeriod) { this.warrantyPeriod = warrantyPeriod; }

    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }



    
}
