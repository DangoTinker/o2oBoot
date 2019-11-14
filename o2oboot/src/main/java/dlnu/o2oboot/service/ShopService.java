package dlnu.o2oboot.service;

import dlnu.o2oboot.dto.ImageHolder;
import dlnu.o2oboot.dto.ShopExecution;
import dlnu.o2oboot.entity.Shop;

import java.util.List;

public interface ShopService {


    public ShopExecution addShop(Shop shop, ImageHolder imageHolder);

    public Shop getShopById(Long shopId);

    public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder);

    public List<Shop> getShops();

    public ShopExecution getShopList(Shop shop, int rowIndex, int pageSize);

}
