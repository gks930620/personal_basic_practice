package Entity;

import javax.persistence.*;

@Entity
public class Child {

    @Id@GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        if(this.parent!=null){
            this.parent.getChildren().remove(this);
        }
        this.parent=parent;
        parent.getChildren().add(this);

    }
}
