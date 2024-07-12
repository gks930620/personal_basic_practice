package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryOld memberRepository;  // final 권장
    //생성자 주입을 하는 이유는 앱 구동시  순환참조되면 에러남.
    // 필드 주입은  앱 구동은 잘됨(빈 생성 잘함), 근데 실행 중 에러남
    // 근데 롬복의 @RequiredArgsConstructor 사용, final이 있는 필드만 생성자 생성



    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원 ");
        }
    }

    //회원 전체  조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member= memberRepository.findOne(id);
        member.setName(name);
    } // @Trasactional 에 의해서  이 메소드 다 하고 commit => 하는순간 변경감지
}
