package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출 시마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //2. 참조값이 다른 것을 확인
        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        //3. memberservice1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

}