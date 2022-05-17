package com.example.readingisgood.exception;

import com.example.readingisgood.constant.SystemMessage;
import com.example.readingisgood.dto.BaseResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerResource extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerResource.class);

    @ExceptionHandler({RuntimeException.class})
    public @ResponseBody BaseResponseDTO runtimeExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO baseResponseDTO = BaseResponseDTO.builder().errorType("systemError").message(SystemMessage.SYSTEM_ERROR.name()).build();
        logger.error("[handleAllExceptions()] response:{}", baseResponseDTO);
        return baseResponseDTO;
    }

    @ExceptionHandler({FriendlyException.class})
    public @ResponseBody BaseResponseDTO friendlyExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO baseResponseDTO = BaseResponseDTO.builder().errorType("friendlyError").message(((FriendlyException) ex).getErrorStr()).build();
        logger.error("[handleAllExceptions()] response:{}", baseResponseDTO);
        return baseResponseDTO;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public @ResponseBody BaseResponseDTO validationExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO baseResponseDTO = BaseResponseDTO.builder().errorType("validationError").message(((ConstraintViolationException) ex).getConstraintViolations().iterator().next().getMessage()).build();
        logger.error("[handleAllExceptions()] response:{}", baseResponseDTO);
        return baseResponseDTO;
    }

    @ExceptionHandler({SecurityException.class, AccessDeniedException.class, BadCredentialsException.class})
    public ResponseEntity<BaseResponseDTO> securityExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO baseResponseDTO = BaseResponseDTO.builder().errorType("securityError").message(ex.getMessage()).build();
        logger.error("[handleAllExceptions()] response:{}", baseResponseDTO);
        return ResponseEntity.badRequest().body(baseResponseDTO);
    }

}