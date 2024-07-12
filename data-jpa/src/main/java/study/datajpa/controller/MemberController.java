package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
//        for(int i=0 ;i<100 ; i++){
//            memberRepository.save(new Member("user" + i,i));
//        }

    }

    //기본은 이렇게 만들텐데 어차피 id는 PK니까  members2처럼 만들자
    @GetMapping("/members/{id}")
    public Member findMmeber(@PathVariable("id") Long id){
        Member member=memberRepository.findById(id).get();
        return member;
    }

    //jpa에서만 된다.. 근데 사용하지말자.  이렇게 단순하게 pk가지고 테이블 조회만 하는거 안쓴다. 특히  조회용으로 사용할때만으로 기능이 한정됨.
    //결국 쓰지말자. 트랜잭션 범위를 조절하기가 힘들다.
    @GetMapping("/members2/{id}")
    public Member findMmeber2(@PathVariable("id") Member member){
        return member;  //따로 member를 DB에서 찾는 코드 없이 알아서 바로 member를 찾아준다.
    }

    //참고로 엔티니는 절대 외부노출 안하는거 알지?
    //글로벌 pageable 기본 값을 바꾸고 싶다면 설정에서 바꿔야지. yml ,properties,xml등
    //MemberController만 기본값 바꾸고 싶다면 @으로
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable){ //Page는 결과데이터, Pageable은 파라미터 데이터
//        PageRequest request = PageRequest.of(1, 2);
//        Page<Member> page = memberRepository.findAll(request);   //pageable 기본 쓰는것보단 이렇게 직접 pageRequest를 만드는게 좋ㄷ.
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> map = page.map(member ->new MemberDto(member));
        return map;
    }




}
