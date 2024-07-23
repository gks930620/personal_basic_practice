package iterator;

public class IteratorFactory  extends  Factory{
    private static IteratorFactory ifactory=new IteratorFactory();
    private IteratorFactory(){

    }
    public static IteratorFactory getInstance(){
        return ifactory;
    }

    @Override
    public Iterator createProduct(Aggregate aggregate, int type) {

        if( type==Constant.FORWARD){
            return new ForwardSehlfIterator(aggregate);
        }else{
            return new ReverseShelfIterator(aggregate);
        }

    }
}
