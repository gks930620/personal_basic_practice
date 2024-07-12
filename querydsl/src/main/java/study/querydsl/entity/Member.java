package study.querydsl.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of= {"id", "username", "age"} )   //toString은 연관관계 필드 제외하자 . 무한루핑 주의
@NamedQuery(name="Member.findByUsername",query = "select m from Member m where m.username =:username"

)

@NamedEntityGraph(name="Member.team" , attributeNodes = @NamedAttributeNode("team"))
public class Member   {
    public Member(String username) {
        this.username = username;
    }
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username= username;
        this.age=age;
        if(team!=null){
            changeTeam(team);
        }

    }

    public void changeTeam(Team team){
        // 이전 팀에서 없애는건 안해도 되나?   한쪽에서만 해야한다. Team에서 한다
        this.team=team;

    }






}
