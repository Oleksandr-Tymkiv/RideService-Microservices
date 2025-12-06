package com.rideserive.users.exceptions;

import com.rideserive.users.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String, Object> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(er -> validationErrors.put(er.getDefaultMessage(), er.getDefaultMessage()));
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .apiPath(request.getDescription(false))
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .errorMessage(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExists(UserAlreadyExistsException ex, WebRequest request){
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .apiPath(request.getDescription(false))
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .errorMessage(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .apiPath(request.getDescription(false))
                        .errorCode(HttpStatus.NOT_FOUND)
                        .errorMessage(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                , HttpStatus.NOT_FOUND
        );
    }
}