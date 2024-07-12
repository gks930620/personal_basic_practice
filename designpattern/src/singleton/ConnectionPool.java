package singleton;

public class ConnectionPool {
    private static ConnectionPool instance=new ConnectionPool();

    private ConnectionPool(){}

    public static ConnectionPool getInstance(){
        return  instance;  //this.instacne아님.  this는 객체를 의미
    }

}
