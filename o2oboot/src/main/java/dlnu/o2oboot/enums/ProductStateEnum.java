package dlnu.o2oboot.enums;

public enum ProductStateEnum {
    OFFLINE(-1, "非法商品"),
    DOWN(0, "下架"),
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "商品为空");;

    private int state;

    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public int getState() {
        return state;
    }



    public static ProductStateEnum stateOf(int state){
        for(ProductStateEnum v:values()){
            if(v.getState()==state){
                return v;
            }
        }
        return null;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
