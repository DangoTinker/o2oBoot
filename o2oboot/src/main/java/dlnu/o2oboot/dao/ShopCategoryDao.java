package dlnu.o2oboot.dao;

import dlnu.o2oboot.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {


    public List<ShopCategory> queryShopCategory(
            @Param("shopCategoryCondition") ShopCategory shopCategory);

}
