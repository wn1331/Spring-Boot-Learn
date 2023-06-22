package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        //멤버객체생성
        Member member = new Member(1L,"MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L,"item1",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
