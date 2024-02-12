package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({AspectV5Order.LogAspect.class, AspectV5Order.TranactionAspect.class})
@Slf4j
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        System.out.println("isAopProxy, orderService= " + AopUtils.isAopProxy(orderService));
        System.out.println("isAopProxy, orderRepository= " + AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success(){
        orderService.orderItem("itemA");
    }
}
