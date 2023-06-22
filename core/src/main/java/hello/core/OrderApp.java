package hello.core;

import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {


    public static void main(String[] args) {
        //멤버서비스,주문서비스
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        //멤버객체생성
        Member member = new Member(1L,"MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L,"item1",10000);
        System.out.println(order.toString());
        System.out.println(order.calculatePrice());
    }

}
