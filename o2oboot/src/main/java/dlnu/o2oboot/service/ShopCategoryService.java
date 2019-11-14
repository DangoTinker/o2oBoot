package dlnu.o2oboot.service;

import dlnu.o2oboot.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    public static final String SCLISTKEY="shopcategorylist";

    public List<ShopCategory> queryShopCategoryList(ShopCategory shopCategory);

}
