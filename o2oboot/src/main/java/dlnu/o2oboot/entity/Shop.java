package dlnu.o2oboot.entity;

import java.util.Date;

public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String shopImg;
    private String phone;

    private Integer enableStatus;
    private Integer priority;

    private String advice;

    private Date createTime;
    private Date lastEditTime;

    private Area area;
    private ShopCategory shopCategory;
    private PersonInfo owner;

    public Shop(){}

    public Shop(String shopName, String shopDesc, String shopAddr, String shopImg, String phone, Integer enableStatus, Integer priority, String advice, Date createTime, Date lastEditTime, Area area, ShopCategory shopCategory, PersonInfo personInfo) {
        this.shopName = shopName;
        this.shopDesc = shopDesc;
        this.shopAddr = shopAddr;
        this.shopImg = shopImg;
        this.phone = phone;
        this.enableStatus = enableStatus;
        this.priority = priority;
        this.advice = advice;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.area = area;
        this.shopCategory = shopCategory;
        this.owner = personInfo;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public String getShopImg() {
        return shopImg;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getAdvice() {
        return advice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public Area getArea() {
        return area;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public PersonInfo getOwner() {
        return owner;
    }
}
