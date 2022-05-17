package com.example.readingisgood.dto;

import com.example.readingisgood.constant.SystemMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponseDTO {

    private boolean success;

    private String message;

    private String errorType;

    @Override
    public String toString() {
        return "BaseResponseDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", errorType='" + errorType + '\'' +
                '}';
    }
}
