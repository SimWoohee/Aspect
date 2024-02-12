package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV5Order {


    /*
    어드바이스의 순서를 정하기 위해 내부클래스를 사용하고 @Order를 적용
     */

    @Aspect
    @Order(2)
    public static class  LogAspect{
        @Around("hello.aop.order.aop.PointCuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class  TranactionAspect{
        @Around("hello.aop.order.aop.PointCuts.orderAndService()")
        public Object doTranaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            }catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            }finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }

}
