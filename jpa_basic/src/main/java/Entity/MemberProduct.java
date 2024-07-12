package Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MemberProductId.class)
@Table(name = "member_product")
public class MemberProduct  implements Serializable {
    @Id@ManyToOne
    @JoinColumn(name = "member_id")   //member쪽에서는 역방향 추가.
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
