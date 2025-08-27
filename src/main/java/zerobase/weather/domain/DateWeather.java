package zerobase.weather.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;
import java.time.LocalDate;

import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DateWeather {
    @Id
    private LocalDate date;
    private String weather;
    private String icon;
    private Double temperature;
}
