package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Lob;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Spring Bean으로 등록
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext // JPA 사용을 위한 설정
    //Spring boot 에서 @PersistenceContext -> @Autowired 로 대체 가능 -> @RequiredArgsConstructor 사용 가능
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // sql 이랑 비슷하지만, from 대상이 테이블이 아니라 entity로 해야된다.
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name",name).getResultList();
    }
}
