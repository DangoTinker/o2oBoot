package dlnu.o2oboot.service;

import dlnu.o2oboot.dto.UserProductMapExecution;
import dlnu.o2oboot.entity.UserProductMap;
import dlnu.o2oboot.exceptions.UserProductMapOperationException;

public interface UserProductMapService {
	/**
	 * 通过传入的查询条件分页列出用户消费信息列表
	 * 
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex,
                                               Integer pageSize);


	UserProductMapExecution addUserProductMap(UserProductMap userProductMap) throws UserProductMapOperationException;

}
