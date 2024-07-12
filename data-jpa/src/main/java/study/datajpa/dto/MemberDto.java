package study.datajpa.dto;


import lombok.Data;
import study.datajpa.entity.Member;

@Data  //dto는 get만 넣는게 일반적..   보여주기용이니까
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    //DTO는 Entity를 매개변수로 받아서 무언가 해도 된다.  그 반대는 절대 x
    public MemberDto(Member member){
        this.id=member.getId();
        this.username=member.getUsername();
    }
}
