package composite;

import java.util.ArrayList;

public class Category  extends  ProductCategory{
    //실제일을 하는 composite
    //복합객체
    ArrayList<ProductCategory> list;

    public Category(int id, String name, int price) {
        super(id, name, price);
        list=new ArrayList<>();
    }

    @Override
    public void addProductCategory(ProductCategory productCategory) {
        list.add(productCategory);
    }

    @Override
    public void removeProductCategory(ProductCategory productCategory) {
        boolean isNotRemoved = !list.remove(productCategory);
        if(isNotRemoved){ //제거되지 않았다면 바로 자식이 아닐 거임.
            for( ProductCategory temp : list){
                temp.removeProductCategory(productCategory); //자식카테고리한테 자식제품 삭제하라고 맞김.
            }
        }


    }

    @Override
    public int getCount() {
        int count=0;
        for(ProductCategory temp : list){
            count+=temp.getCount();
        }
        return count;
    }

    @Override
    public int getPrice() {
        int price=0;
        for(ProductCategory temp : list){
            price+=temp.getPrice();
        }
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }
}
