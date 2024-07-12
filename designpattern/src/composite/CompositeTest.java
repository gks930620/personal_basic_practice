package composite;

public class CompositeTest {
    public static void main(String[] args) {
        //Product 클래스(Leaf)가 추가되어도 전체 코드에는 영향을 끼치지 않음
        ProductCategory womanCategory=new Category(1234,"Woman" ,0);
        ProductCategory manCategory=new Category(5678,"man" ,0); //남자도 하긴해야되는데 컨셉만...  여자카테고리에만 데이터 넣자.

        ProductCategory clothesCategoryW=new Category(2345,"Clothes",0);
        ProductCategory bagCategoryW=new Category(3456,"Bag",0);
        ProductCategory shoesCategoryW=new Category(9876,"Shoes",0);
        womanCategory.addProductCategory(clothesCategoryW);
        womanCategory.addProductCategory(bagCategoryW);
        womanCategory.addProductCategory(shoesCategoryW);   //상위 카테고리에 하위카테고리 넣음

        ProductCategory shoes1=new Product(121,"Nike" , 10000);
        ProductCategory shoes2=new Product(122,"Adidas" , 20000);
        ProductCategory shoes3=new Product(123,"NORTH" , 30000);
        shoesCategoryW.addProductCategory(shoes1);
        shoesCategoryW.addProductCategory(shoes2);
        shoesCategoryW.addProductCategory(shoes3);

        ProductCategory bag1= new Product(121,"HERMES",50000);
        ProductCategory bag2= new Product(122,"LOUISVUITTON",50000);
        ProductCategory bag3= new Product(123,"GUCCI",50000);
        bagCategoryW.addProductCategory(bag1);
        bagCategoryW.addProductCategory(bag2);
        bagCategoryW.addProductCategory(bag3);

        System.out.println(womanCategory.getCount());
        System.out.println(womanCategory.getPrice());  //여자들 수량, 가격 다 나옴

        womanCategory.removeProductCategory(bagCategoryW);
        System.out.println(womanCategory.getCount());  //카테고리를 삭제

        womanCategory.removeProductCategory(shoes1);  //제품1개 삭제?
        System.out.println(womanCategory.getCount());

    }
}
