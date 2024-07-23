package iterator;

public class ForwardSehlfIterator implements  Iterator{

    BookShelf bookShelf;
    private int index;
    public ForwardSehlfIterator(Aggregate list ) {
        bookShelf=(BookShelf) list;
        index=0;
    }

    @Override
    public boolean hasNext() {
        if(index<bookShelf.getLength()){
            return true;
        }
        return false;
    }
    @Override
    public Object next() {
        Book book=bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
