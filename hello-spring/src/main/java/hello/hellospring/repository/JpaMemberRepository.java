package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member; // 이렇게 하면 id까지 다 들어간 member가 반환됨
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // find(조회할 타입, 식별자)
        return Optional.ofNullable(member); // null이어도 감싸서 반환 가능
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    } // JPQL이라는 객체지향 쿼리 언어 사용

    @Override
    public List<Member> findAll() {
        // JPQL이라는 객체지향 쿼리 언어 사용
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // JPQL은 테이블이 아닌 객체를 대상으로 쿼리를 날림
    }
}
