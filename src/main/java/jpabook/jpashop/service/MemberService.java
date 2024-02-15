package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 쓸 수 있는 옵션이 더 많아서 스프링 transactional을 사용한다.
                                // (readOnly = true) 조회할 때 성능 더 좋다.
@RequiredArgsConstructor    //lombok Annotation
public class MemberService {

    //@Autowired //Spring Bean에 등록된 repository를 injection 해준다.
    private final MemberRepository memberRepository;

    //@RequiredArgsConstructor 으로 대체 가능
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    }

    //회원가입
    @Transactional
    public Long join(Member member){
        validateDuplidateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplidateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        //Exception
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 1명 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
