package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class) // junit5 설정 (junit4인 경우 RunWith)
@SpringBootTest
class MemberRepositoryTest {

        @Autowired MemberRepository memberRepository;

        @Test
        @Transactional
        @Rollback(false)
        public void testMember() throws Exception {
            //given
            Member member = new Member();
            member.setUsername("memberA");

            //when
            Long savedId = memberRepository.save(member);
            Member findMeber = memberRepository.find(savedId);

            //then
            Assertions.assertThat(findMeber.getId()).isEqualTo(member.getId());
            Assertions.assertThat(findMeber.getUsername()).isEqualTo(member.getUsername());
            Assertions.assertThat(findMeber).isEqualTo(member);
        }
}