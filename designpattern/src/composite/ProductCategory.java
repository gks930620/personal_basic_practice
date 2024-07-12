package composite;

import abstractfactory.domain.product.Product;

public abstract class ProductCategory {
    int id;
    String name;
    int price;  //카테고리는 가격이 따로 없고 가지고 있는 모든거 합친 가격
    public ProductCategory(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public abstract  void addProductCategory(ProductCategory productCategory);
    public abstract  void removeProductCategory(ProductCategory productCategory);
    public abstract int getCount();
    public abstract int getPrice();
    public abstract String getName();
    public abstract int getId();
}



