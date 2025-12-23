package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService =beanFactory.getBean(PaymentService.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        System.out.println(
                paymentService.exRateProvider == orderService.exRateProvider
        );
//        PaymentService paymentService2 =beanFactory.getBean(PaymentService.class);
//
//        System.out.println(paymentService);
//        System.out.println(paymentService2);
//        System.out.println(paymentService == paymentService2);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
