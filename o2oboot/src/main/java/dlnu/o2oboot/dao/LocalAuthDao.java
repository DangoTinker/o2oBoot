package dlnu.o2oboot.dao;

import dlnu.o2oboot.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LocalAuthDao {

    public LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username,
                                                @Param("password") String password);
    public LocalAuth queryLocalByUserId(@Param("userId") Long userId);

    public int insertLocalAuth(LocalAuth localAuth);

    public int updateLocalAuth(
            @Param("userId") Long userId,
            @Param("username") String username,
            @Param("password") String password,
            @Param("newPassword") String newPassword,
            @Param("lastEditTime") Date lastEditTime
    );

}
