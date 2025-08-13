package ru.sidorov.currencyproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "DTO для передачи информации об ошибке")
public class ErrorDto {
    @Schema(
            description = "Сообщение об ошибке",
            example = "Currency not found"
    )
    private String message;

    @Schema(
            description = "Временная метка возникновения ошибки",
            example = "2023-05-15T14:30:45.123456"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "HTTP статус код ошибки",
            example = "404"
    )
    private int status;

    private ErrorDto(Builder builder) {
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.status = builder.status;
    }

    public static class Builder{
        private String message;
        private LocalDateTime timestamp;
        private int status;

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp){
            this.timestamp = timestamp;
            return this;
        }
        public Builder status(int status){
            this.status = status;
            return this;
        }

        public ErrorDto build(){
            return new ErrorDto(this);
        }

    }
    public static Builder builder() {
        return new Builder();
    }
}