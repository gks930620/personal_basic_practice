package prototype;

import java.util.ArrayList;

class Book {
    private  String author;
    private String title;
    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Book{" + "author='" + author + '\'' + ", title='" + title + '\'' + '}';
    }
}

class  BookShelf implements Cloneable{ //Cloneable을 안하면 clone못함 (컴파일은 되는데.  실행시 run 됨
    //mark interface : 구현할 메소드가 따로 없음.

    private ArrayList<Book> shelf;   //왜 List타입으로 선언 안한걸까..

    public BookShelf(){
        shelf=new ArrayList<>();
    }

    public void addBook(Book book){
        shelf.add(book);
    }


    public ArrayList<Book> getShelf() {
        return shelf;
    }

    public void setShelf(ArrayList<Book> shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "bookshelf"+shelf.toString();  //책장이랑 ArrayList<Book>은 구별되야되니까
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BookShelf another= new BookShelf();
        for(Book book : shelf){
            another.addBook(new Book(book.getAuthor(), book.getTitle()));
        }
        return another;
    }
}

public class PrototypeTest{
    public static void main(String[] args) throws CloneNotSupportedException {
        BookShelf bookShelf=new BookShelf();
        bookShelf.addBook(new Book("조정래" , "태백산맥"));
        bookShelf.addBook(new Book( "박완서", "나목"));
        bookShelf.addBook(new Book("박경리" , "토지"));
        System.out.println(bookShelf);
        BookShelf another=(BookShelf) bookShelf.clone();
        System.out.println(another);

        //super.clone()일 때
//        System.out.println(another==bookShelf);//BookShelf는 서로다른 메모리
//        System.out.println(another.getShelf()==bookShelf.getShelf());
        //필드는 같은 곳 참조 (another를 새로 만들었지만, 그 안의 필드는 새로 안 만들고 있는거 사용

        //overwriting 했을 때
        System.out.println(another==bookShelf);// 다름
        System.out.println(another.getShelf()==bookShelf.getShelf()); //다름.




    }
}
