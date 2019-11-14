package dlnu.o2oboot.service.impl;

import dlnu.o2oboot.dao.ShopDao;
import dlnu.o2oboot.dto.ImageHolder;
import dlnu.o2oboot.dto.ShopExecution;
import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.enums.ShopStateEnum;
import dlnu.o2oboot.service.ShopService;
import dlnu.o2oboot.util.ImageUtil;
import dlnu.o2oboot.util.PageCalculator;
import dlnu.o2oboot.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;


    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) {
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }

        try{
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            int n=shopDao.insertShop(shop);
            if(n<=0){
                throw new RuntimeException("添加失败");
            }
            addShopImg(shop,imageHolder.getImage(),imageHolder.getImageName());
            n=shopDao.updateShop(shop);

            if(n<=0){
                throw new RuntimeException("更新地址失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("失败");
        }

        return new ShopExecution(ShopStateEnum.CHECK,shop);

    }

    @Override
    public Shop getShopById(Long shopId) {
        return shopDao.queryShopById(shopId);
    }

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) {
        if(shop==null||shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }
        int n;
        try{
            if(imageHolder.getImage()!=null&&imageHolder.getImageName()!=null&&!imageHolder.getImageName().equals("")){
                Shop temp=shopDao.queryShopById(shop.getShopId());
                System.out.println(temp.getShopImg()!=null);
                if(temp.getShopImg()!=null){
                    ImageUtil.deleteFileOrPath(temp.getShopImg());
                }
                addShopImg(shop,imageHolder.getImage(),imageHolder.getImageName());
            }

            n=shopDao.updateShop(shop);

            if(n<1){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }
            return new ShopExecution(ShopStateEnum.SUCCESS,shop);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新错误");
        }

    }

    @Override
    public List<Shop> getShops() {
        List<Shop> list=shopDao.queryShops();


        return list;
    }

    @Override
    public ShopExecution getShopList(Shop shop, int pageIndex, int pageSize) {
        List<Shop> list=shopDao.queryShopList(shop, PageCalculator.calculateRowIndex(pageIndex,pageSize),pageSize);
        int count= shopDao.queryShopCount(shop);
        ShopExecution se=null;
        if(list!=null){
            se=new ShopExecution(ShopStateEnum.SUCCESS,list);
            se.setCount(count);

        }else{
            se=new ShopExecution(ShopStateEnum.INNER_ERROR);
        }



        return se;

    }


    private void addShopImg(Shop shop,InputStream file,String fileName){
        String imagePath= PathUtil.getShopImagePath(shop.getShopId());
        String res=ImageUtil.generateThumbnail(file,fileName,imagePath);
        shop.setShopImg(res);

    }


}
