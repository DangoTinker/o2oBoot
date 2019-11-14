package dlnu.o2oboot.dto;

import dlnu.o2oboot.entity.ProductCategory;
import dlnu.o2oboot.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    private int state;
    private String stateInfo;

    private ProductCategory productCategory;
    private List<ProductCategory> productCategoryList;


    public ProductCategoryExecution(ProductCategoryStateEnum enmu) {
        this.state=enmu.getState();
        this.stateInfo=enmu.getStateInfo();
    }

    public ProductCategoryExecution(ProductCategoryStateEnum enmu, ProductCategory productCategory) {
        this.state=enmu.getState();
        this.stateInfo=enmu.getStateInfo();
        this.productCategory = productCategory;
    }

    public ProductCategoryExecution(ProductCategoryStateEnum enmu, List<ProductCategory> productCategoryList) {
        this.state=enmu.getState();
        this.stateInfo=enmu.getStateInfo();
        this.productCategoryList = productCategoryList;
    }


    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }
}
