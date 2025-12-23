package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

@Configuration
//@ComponentScan
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
//        return new SimpleExRateProvider();
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

//    @Bean
//    public OrderService orderService() {
//        return new OrderService(exRateProvider());
//    }
}

//class OrderService {
//    ExRateProvider exRateProvider;
//
//    public OrderService(ExRateProvider exRateProvider) {
//        this.exRateProvider = exRateProvider;
//    }
//}
