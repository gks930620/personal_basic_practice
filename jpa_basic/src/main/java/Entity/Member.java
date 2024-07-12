package Entity;

import common.Address;
import common.AddressEntity;
import common.Period;
import type.MemberType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@NamedQuery(
        name  ="Member.findByName",
        query = "select m from Member m where  m.name= :name "
)
public class Member {


    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private  int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id" )
    private Team team;

    @OneToMany(mappedBy = "member")
    List<Order> orders= new ArrayList<Order>();


    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;


    @ElementCollection
    @CollectionTable(name = "favorite_foods",
            joinColumns = { @JoinColumn(name = "member_id")  } )
    @Column(name = "food_name")
    private List<String> favoriteFoods= new ArrayList<>();


//    @ElementCollection
//    @CollectionTable(name = "address_history",
//    joinColumns = { @JoinColumn(name = "member_id")})
//    private Set<Address> addressHistory= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressEntity> addressHistory= new ArrayList<>();


    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getMembers().remove(this);  //기존팀의 관계를 제거
        }
        this.team = team;   //팀을 새로운 팀으로 변경
        if(team!=null){
            team.getMembers().add(this); // 새로운팀에 관계 추가
        }

    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public List<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}













//    public void setTeam(Entity.Team team) {
//        if(this.team !=null){
//            this.team.getMembers().remove(this);  //기존팀의 관계를 제거
//        }
//        this.team = team;   //팀을 새로운 팀으로 변경
//        team.getMembers().add(this); // 새로운팀에 관계 추가
//    }