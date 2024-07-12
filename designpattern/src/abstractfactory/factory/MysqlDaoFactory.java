package abstractfactory.factory;

import abstractfactory.domain.product.dao.MysqlProductDao;
import abstractfactory.domain.product.dao.ProductDao;
import abstractfactory.domain.userinfo.dao.MysqlUserInfoDao;
import abstractfactory.domain.userinfo.dao.UserInfoDao;

public class MysqlDaoFactory implements  DaoFactory{
    @Override
    public UserInfoDao createUserInfoDao() {
        return new MysqlUserInfoDao();
    }

    @Override
    public ProductDao createProductDao() {
        return new MysqlProductDao();
    }
}
