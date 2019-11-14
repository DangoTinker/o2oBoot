package dlnu.o2oboot.service;

import dlnu.o2oboot.dto.ProductCategoryExecution;
import dlnu.o2oboot.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    public List<ProductCategory> getProductCategoryByshopId(Long shopId);
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> list);

    public ProductCategoryExecution deleteProductCategory(Long productCategory, Long shopId);

}
