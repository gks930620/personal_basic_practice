package abstractfactory;

import abstractfactory.domain.product.Product;
import abstractfactory.domain.userinfo.UserInfo;
import abstractfactory.domain.userinfo.dao.UserInfoDao;
import abstractfactory.factory.DaoFactory;
import abstractfactory.factory.MysqlDaoFactory;
import abstractfactory.factory.OracleDaoFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WebClientMain {
    public static void main(String[] args) throws Exception {
        //properties에서 mysql로 있으면 mysql 선택
        FileInputStream fis=new FileInputStream("db.properties");
        Properties prop=new Properties();
        prop.load(fis);
        String dbType=prop.getProperty("DBTYPE");

        UserInfo userInfo=new UserInfo();
        userInfo.setUserId("12345");
        userInfo.setPassword("!@#$%");
        userInfo.setUserName("이순신");

        Product product=new Product();
        product.setProductId("0011AA");
        product.setProductName("TV");

        DaoFactory daoFactory=null;
        if (dbType.equals("MYSQL")){
            daoFactory=new MysqlDaoFactory();
        }else if(dbType.equals("ORACLE")){
            daoFactory=new OracleDaoFactory();
        }else{
            throw new Exception("맞는 DB가 없음");
        }

        UserInfoDao userInfoDao= daoFactory.createUserInfoDao();;
        userInfoDao.insertProduct(userInfo); //하면 알아서 properties에 맞는 dao가 실행됨


    }
}
