package dlnu.o2oboot.enums;

public enum ProductCategoryStateEnum {

    SUCCESS(1,"操作成功"),

    INNER_ERROR(-1001,"内部系统错误"),
    EMPTY_LIST(-1002,"添加数少于1");

    private int state;
    private String stateInfo;

    private ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public ProductCategoryStateEnum stateOf(int state){
        for(ProductCategoryStateEnum productStateEnum:values()){
            if (productStateEnum.getState()==state){
                return productStateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}
