package shop.dongho.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "không được để trống")
    private String name;

    private String image;

    private String image1;

    private String image2;

    private String image3;
    @NotEmpty(message = "không được để trống")
    private String color;
    @NotEmpty(message = "không được để trống")
    private String hot;
    @NotEmpty(message = "không được để trống")
    private String machineType;
    @NotEmpty(message = "không được để trống")
    private String size;
    @NotEmpty(message = "không được để trống")
    private String materialShell;
    @NotEmpty(message = "không được để trống")
    private String waterproof;
    @NotEmpty(message = "không được để trống")
    private String faceGlass;
    @NotEmpty(message = "không được để trống")
    private String detail;
    @NotEmpty(message = "không được để trống")
    private String unitPrice;
    @NotEmpty(message = "không được để trống")
    private String promotionPrice;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @ManyToOne(targetEntity = Producer.class)
    @JoinColumn(name = "idProducer")
    private Producer producer;

    @ManyToOne(targetEntity = ProductType.class)
    @JoinColumn(name = "idProductType")
    private ProductType productType;

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Product() {

    }

    public Product(@NotEmpty(message = "không được để trống") String name, String image, String image1, String image2, String image3, @NotEmpty(message = "không được để trống") String color, @NotEmpty(message = "không được để trống") String hot, @NotEmpty(message = "không được để trống") String machineType, @NotEmpty(message = "không được để trống") String size, @NotEmpty(message = "không được để trống") String materialShell, @NotEmpty(message = "không được để trống") String waterproof, @NotEmpty(message = "không được để trống") String faceGlass, @NotEmpty(message = "không được để trống") String detail, @NotEmpty(message = "không được để trống") String unitPrice, @NotEmpty(message = "không được để trống") String promotionPrice) {
        this.name = name;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.color = color;
        this.hot = hot;
        this.machineType = machineType;
        this.size = size;
        this.materialShell = materialShell;
        this.waterproof = waterproof;
        this.faceGlass = faceGlass;
        this.detail = detail;
        this.unitPrice = unitPrice;
        this.promotionPrice = promotionPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterialShell() {
        return materialShell;
    }

    public void setMaterialShell(String materialShell) {
        this.materialShell = materialShell;
    }

    public String getWaterproof() {
        return waterproof;
    }

    public void setWaterproof(String waterproof) {
        this.waterproof = waterproof;
    }

    public String getFaceGlass() {
        return faceGlass;
    }

    public void setFaceGlass(String faceGlass) {
        this.faceGlass = faceGlass;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }


}
