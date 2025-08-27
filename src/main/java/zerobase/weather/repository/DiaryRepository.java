package zerobase.weather.repository;

import zerobase.weather.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByDate(LocalDate date);
    List<Diary> findAllByDateBetween(LocalDate start, LocalDate end);
    Optional<Diary> findFirstByDateOrderById(LocalDate date);
    void deleteAllByDate(LocalDate date);
}
