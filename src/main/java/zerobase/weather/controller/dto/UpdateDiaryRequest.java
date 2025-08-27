package zerobase.weather.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UpdateDiaryRequest(
        @Schema(example = "2025-08-22")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        @Schema(example = "수정된 내용")
        String text
) { }
