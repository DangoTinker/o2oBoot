package dlnu.o2oboot.dto;

import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    private int state;
    private String stateInfo;

    private Shop shop;

    private List<Shop> shops;

    private int count;

    public ShopExecution(){}

    public ShopExecution(ShopStateEnum shopStateEnum) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    public ShopExecution(ShopStateEnum shopStateEnum,Shop shop) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop=shop;
    }
    public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shops) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shops=shops;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public List<Shop> getShops() {
        return shops;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }
}
