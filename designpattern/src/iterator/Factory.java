package iterator;

public abstract class Factory {
    public final Iterator create(Aggregate list , int type){
        Iterator p=createProduct(list, type);
        return p;
    }

    public abstract  Iterator createProduct(Aggregate aggregate, int type);

}
