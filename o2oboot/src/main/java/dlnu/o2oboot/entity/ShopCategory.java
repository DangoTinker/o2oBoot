package dlnu.o2oboot.entity;

import java.util.Date;

public class ShopCategory {
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;

    private Date createTime;
    private Date lastEditTime;

    private Integer priority;
    private ShopCategory parent;

    public ShopCategory(){}

    public ShopCategory(String shopCategoryName, String shopCategoryDesc, String shopCategoryImg, Date createTime, Date lastEditTime, Integer priority, ShopCategory parent) {
        this.shopCategoryName = shopCategoryName;
        this.shopCategoryDesc = shopCategoryDesc;
        this.shopCategoryImg = shopCategoryImg;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.priority = priority;
        this.parent = parent;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
    }

    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public ShopCategory getParent() {
        return parent;
    }
}
