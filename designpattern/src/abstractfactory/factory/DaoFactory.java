package abstractfactory.factory;

import abstractfactory.domain.product.dao.ProductDao;
import abstractfactory.domain.userinfo.dao.UserInfoDao;

public interface DaoFactory {
    public UserInfoDao createUserInfoDao();
    public ProductDao createProductDao();

}
