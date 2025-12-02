package com.capg.busticketbooking.exception;

import com.capg.busticketbooking.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message, WebRequest request, Map<String, Object> details) {
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .details(details)
                .build();
        return new ResponseEntity<>(body, status);
    }

    // Custom exception handlers
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage() == null ? "Resource not found" : ex.getMessage(), request, null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, WebRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage() == null ? "Bad request" : ex.getMessage(), request, null);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex, WebRequest request) {
        return build(HttpStatus.CONFLICT, ex.getMessage() == null ? "Conflict" : ex.getMessage(), request, null);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleService(ServiceException ex, WebRequest request) {
        log.error("ServiceException:", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage() == null ? "Service error" : ex.getMessage(), request, Map.of("exception", ex.getClass().getName()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElement(NoSuchElementException ex, WebRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage() == null ? "Resource not found" : ex.getMessage(), request, null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        Map<String, Object> d = new HashMap<>();
        d.put("param", ex.getName());
        d.put("expectedType", ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");
        d.put("value", ex.getValue());
        String msg = String.format("Parameter '%s' should be of type %s", ex.getName(), d.get("expectedType"));
        return build(HttpStatus.BAD_REQUEST, msg, request, d);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex, WebRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Malformed JSON request", request, Map.of("cause", ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String field = (err instanceof FieldError) ? ((FieldError) err).getField() : err.getObjectName();
            String message = err.getDefaultMessage();
            errors.put(field, message);
        });
        return build(HttpStatus.BAD_REQUEST, "Validation failed", request, Map.of("fieldErrors", errors));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, WebRequest request) {
        return build(HttpStatus.CONFLICT, "Data integrity violation", request, Map.of("cause", ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoHandlerFoundException ex, WebRequest request) {
        return build(HttpStatus.NOT_FOUND, "Endpoint not found", request, Map.of("method", ex.getHttpMethod(), "url", ex.getRequestURL()));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponse> handleMissingPathVar(MissingPathVariableException ex, WebRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Missing path variable: " + ex.getVariableName(), request, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
        log.error("Unhandled exception caught by GlobalExceptionHandler", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage() == null ? "Internal Server Error" : ex.getMessage(), request, Map.of("exception", ex.getClass().getName()));
    }
}
