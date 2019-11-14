package dlnu.o2oboot.dao;

import dlnu.o2oboot.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    public List<ProductCategory> queryProductCategoryListByShopId(
            @Param("shopId") Long shopId);


    public int batchInsertProductCategory(List<ProductCategory> list);


    public int deleteProductCategory(@Param("productCategoryId") Long productCategoryId,
                                     @Param("shopId") Long shopId);

}
