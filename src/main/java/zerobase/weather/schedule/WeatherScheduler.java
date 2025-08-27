package zerobase.weather.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.service.WeatherClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {
    private final WeatherClient weatherClient;
    private final DateWeatherRepository dateWeatherRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void saveTodayWeather() {
        var today = weatherClient.fetchCurrent();
        dateWeatherRepository.save(today);
        log.info("[Scheduler] Saved DateWeather for {}", today.getDate());
    }
}
