package study.querydsl.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

public class MemberRepositoryImpl implements  MemberRepositoryCustom {

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }
    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }
    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe !=null ? member.age.goe(ageGoe) : null;
    }
    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe !=null ? member.age.loe(ageLoe) : null;
    }
    private  BooleanExpression ageBetween(Integer ageGoe,Integer ageLoe){
        return ageGoe(ageGoe).and(ageLoe(ageLoe) );
    }

    @Autowired
    private JPAQueryFactory queryFactory;   //난 빈등록했으니까..

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition){
        return queryFactory.select(
                new QMemberTeamDto(member.id.as("memberId"),member.username,member.age, team.id.as("teamId"), team.name.as("teamName"))
        )
                .from(member)
                .leftJoin(member.team,team)
                .where(
                        usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName())
                        ,ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
                ).fetch();
    }


    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory.select(
                new QMemberTeamDto(member.id.as("memberId"), member.username, member.age, team.id.as("teamId"), team.name.as("teamName"))
        )
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName())
                        , ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults(); //fetchResult는 쿼리 + 카운터쿼리 다.

        List<MemberTeamDto> content= results.getResults();
        long total= results.getTotal();
        return new PageImpl<>(content,pageable,total);
    }


    //여러가지장점이 있지만  simple 써도 큰 문제 없다.
    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory.select(
                new QMemberTeamDto(member.id.as("memberId"), member.username, member.age, team.id.as("teamId"), team.name.as("teamName"))
        )
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName())
                        , ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

//        long total = queryFactory.select(member).from(member)
//                .leftJoin(member.team, team)
//                .where(usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName())
//                        , ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
//                ).fetchCount();  //직접 카운트 쿼리 날리기            대부분은 이걸 사용하면 됨.
        //return new PageImpl<>(content,pageable,total);


        //계속 까먹는데 offset은 시작위치   (0이면 0페이지, 10이면 1페이지

        //  countQuery를 날릴  필요가 없을 경우.  전체개수가 1페이지 안에서 처리 될 때 => content.size가 total
        //  마지막페이지 일 때   =>offset+ content.szie 가 total
        //  그 외 카운트쿼리 필요할 땐 알아서 날림.
        JPAQuery<Member> countQuery = queryFactory.select(member).from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName())
                        , ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
                );

        return PageableExecutionUtils.getPage(content,pageable, () -> countQuery.fetchCount());
    }




}
