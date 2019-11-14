package dlnu.o2oboot.entity;

import java.util.Date;

public class ProductCategory {
    private Long productCategoryId;
    private String productCategoryName;
    private Integer priority;
    private Date createTime;


    private Long shopId;


    public ProductCategory(){}


    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }






    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }


    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }



    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }


}
