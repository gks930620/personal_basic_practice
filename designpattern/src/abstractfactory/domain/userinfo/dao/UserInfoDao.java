package abstractfactory.domain.userinfo.dao;

import abstractfactory.domain.product.Product;
import abstractfactory.domain.userinfo.UserInfo;

public interface UserInfoDao {
    void insertProduct(UserInfo userInfo);
    void updateProduct(UserInfo userInfo);
    void deleteProduct(UserInfo userInfo);

}
