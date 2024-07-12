package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberSpringJpaController {
    private final MemberRepository memberRepository;

    @GetMapping("/spring/jpa/v1/members")
    public List<Member> membersV1(String name){
        return memberRepository.findByName(name);
    }
}
