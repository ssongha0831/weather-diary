package zerobase.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import zerobase.weather.domain.DateWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    @Value("${openweather.base-url}") private String baseUrl;
    @Value("${openweather.city}")     private String city;
    @Value("${openweather.api-key}")  private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DateWeather fetchCurrent() {
        String url = String.format("%s/weather?q=%s&appid=%s&units=metric&lang=kr",
                baseUrl, city, apiKey);

        ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
        try {
            JsonNode root = objectMapper.readTree(res.getBody());
            String main = root.path("weather").get(0).path("main").asText();
            String icon = root.path("weather").get(0).path("icon").asText();
            double temp = root.path("main").path("temp").asDouble();
            return DateWeather.builder()
                    .date(LocalDate.now())
                    .weather(main)
                    .icon(icon)
                    .temperature(temp)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Weather API 응답 파싱 실패", e);
        }
    }
}
