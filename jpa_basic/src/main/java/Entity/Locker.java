package Entity;

import javax.persistence.*;

@Entity
public class Locker {

    @Id@GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
