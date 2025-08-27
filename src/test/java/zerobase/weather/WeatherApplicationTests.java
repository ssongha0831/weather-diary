package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WeatherApplicationTests {
    @Test
    void contextLoads() { assertThat(1).isEqualTo(1); }

    @Test
    void sampleAssert() { assertThat("weather").startsWith("wea").endsWith("ther"); }
}
