package tobyspring.hellospring.api;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extract(String response);
}
