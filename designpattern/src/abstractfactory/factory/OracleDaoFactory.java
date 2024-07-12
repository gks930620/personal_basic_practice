package abstractfactory.factory;

import abstractfactory.domain.product.dao.MysqlProductDao;
import abstractfactory.domain.product.dao.OracleProductDao;
import abstractfactory.domain.product.dao.ProductDao;
import abstractfactory.domain.userinfo.dao.OracleUserInfoDao;
import abstractfactory.domain.userinfo.dao.UserInfoDao;

public class OracleDaoFactory implements  DaoFactory{
    @Override
    public UserInfoDao createUserInfoDao() {
        return new OracleUserInfoDao();
    }

    @Override
    public ProductDao createProductDao() {
        return new OracleProductDao();
    }
}
