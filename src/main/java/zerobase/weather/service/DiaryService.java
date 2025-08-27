package zerobase.weather.service;

import zerobase.weather.domain.DateWeather;
import zerobase.weather.domain.Diary;
import zerobase.weather.exception.InvalidDateException;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;
    private final WeatherClient weatherClient;

    @Transactional
    public void createDiary(LocalDate date, String text) {
        DateWeather dw = dateWeatherRepository.findByDate(date)
                .orElseGet(() -> {
                    if (date.equals(LocalDate.now())) {
                        DateWeather current = weatherClient.fetchCurrent();
                        return dateWeatherRepository.save(current);
                    }
                    throw new InvalidDateException("해당 날짜의 날씨 데이터가 없습니다.");
                });

        Diary diary = Diary.builder()
                .date(date)
                .text(text)
                .weather(dw.getWeather())
                .icon(dw.getIcon())
                .temperature(dw.getTemperature())
                .build();
        diaryRepository.save(diary);
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiary(LocalDate date) {
        return diaryRepository.findAllByDate(date);
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiaries(LocalDate start, LocalDate end) {
        return diaryRepository.findAllByDateBetween(start, end);
    }

    @Transactional
    public void updateDiary(LocalDate date, String newText) {
        Diary target = diaryRepository.findFirstByDateOrderById(date)
                .orElseThrow(() -> new InvalidDateException("수정할 일기가 없습니다."));
        target.setText(newText);
    }

    @Transactional
    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }
}
