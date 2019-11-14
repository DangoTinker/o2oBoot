package dlnu.o2oboot.dao;

import dlnu.o2oboot.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    public int insertShop(Shop shop);
    public int updateShop(Shop shop);

    public Shop queryShopById(@Param("shopId") Long shopId);

    public List<Shop> queryShops();

    public List<Shop> queryShopList(@Param("shopCondition") Shop shop, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    public int queryShopCount(@Param("shopCondition") Shop shop);
}

