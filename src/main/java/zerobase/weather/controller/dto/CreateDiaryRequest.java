package zerobase.weather.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CreateDiaryRequest(
        @Schema(example = "2025-08-22")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        @Schema(example = "오늘 날씨 좋음")
        String text
) { }
