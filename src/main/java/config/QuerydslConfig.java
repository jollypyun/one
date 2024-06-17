package config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig {
    /*
    * PersistenceContext: EntityManager 를 빈을로 주입할 때 사용하는 어노테이션
    * 스프링에서 영속성 관리를 위해 EntityManager 가 존재. 스프링 컨테이너가 시작될 때 EntityManager 를 만들어 빈으로 등록.
    * 여러 쓰레드가 동시에 접근하면 동시에 접근하면 동시성 문제가 발생하여 공유해서는 안 된다.
    * PersistenceContext 로 인해 EntityManager를 Proxy로 감싸고 호출 시 Proxy를 통해 EntityManager를 생성해 Thread-safe를 보장
    * */
//    @PersistenceContext
    private final EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
