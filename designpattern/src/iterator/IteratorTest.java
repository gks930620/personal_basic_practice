package iterator;

public class IteratorTest {
    public static void main(String[] args) {
        BookShelf bookShelf=new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Chinderlla"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));

        Iterator forward= bookShelf.iterator(Constant.FORWARD);
        //제네릭을 쓰면 형변환 없이 가능
        while (forward.hasNext()){
            Book book=(Book) forward.next();
            System.out.println("" + book.getName());
        }

        Iterator reverse= bookShelf.iterator(Constant.REVERSE);
        while (reverse.hasNext()){
            Book book = (Book) reverse.next();
            System.out.println("" + book.getName());
        }

    }
}
