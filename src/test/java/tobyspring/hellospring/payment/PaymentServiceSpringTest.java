package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

//    @Autowired BeanFactory beanFactory;
    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {

        // exRate : 1000
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        System.out.println(payment);
        // 환율정보 가져온다 돈과관련된것은 BigDecimal로 비교는 isEqualByComparingTo로 하는것이 좋음
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        // 원화 환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // exRate : 500
        System.out.println(exRateProviderStub);
        exRateProviderStub.setExRate(valueOf(500));
        System.out.println(exRateProviderStub);

        System.out.println();
        System.out.println(paymentService.exRateProvider);
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        System.out.println(payment2);

        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

}