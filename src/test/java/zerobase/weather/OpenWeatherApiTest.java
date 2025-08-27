package zerobase.weather;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OpenWeatherApiTest {
    @Test
    @DisplayName("온도/아이콘/날씨필드 존재 가정 샘플")
    void fieldShapeSample() {
        assertThat("main").isEqualTo("main");
        assertThat("icon").isEqualTo("icon");
        assertThat("temp").isEqualTo("temp");
    }
}
