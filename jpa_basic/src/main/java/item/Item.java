package item;


import common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE" )   //기본값이 DTYPE
public abstract  class Item  extends BaseTimeEntity {

    @Id@GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private  String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
