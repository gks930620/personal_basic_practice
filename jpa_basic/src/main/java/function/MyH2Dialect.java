package function;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect  extends H2Dialect {
    public  MyH2Dialect(){
        registerFunction("group_concat" , new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
        //새로만드는건 아니고, 방언에는 없지만 DB에서는 쓸 수 있는 함수 등록.  수업X 하지말자.
    }

}
