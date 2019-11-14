package dlnu.o2oboot.dao;

import dlnu.o2oboot.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {


    public int insertProductImg(ProductImg productImg);

    public int batchInsertProductImg(List<ProductImg> list);

    public int deleteProductImgByProductId(Long productId);
    public List<ProductImg> queryProductImgListByProductId(Long productId);
}
