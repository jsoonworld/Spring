package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 개겣를 생성
        MemberService memeberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 개겣를 생성
        MemberService memeberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memeberService2 = " + memeberService1);
        System.out.println("memeberService2 = " + memeberService2);

        //memberService != memberService2
        Assertions.assertThat(memeberService1).isNotSameAs(memeberService2);


    }

    @Test
    @DisplayName("싱극톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memeberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memeberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memeberService2 = " + memeberService1);
        System.out.println("memeberService2 = " + memeberService2);

        //memberService != memberService2
        Assertions.assertThat(memeberService1).isSameAs(memeberService2);


    }
}
