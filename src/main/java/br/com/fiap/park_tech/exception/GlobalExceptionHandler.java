package br.com.fiap.park_tech.exception;

import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotAlreadyOcuppiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(EntityAlreadyDeletedException.class)
    public ResponseEntity<Object> handleEntityAlreadyDeletedException(EntityAlreadyDeletedException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.GONE.value());
        body.put("error", "Gone");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4)); // Remove 'uri=' prefix

        return new ResponseEntity<>(body, HttpStatus.GONE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(VehiclerNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", ex.getMessage());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParkingSlotAlreadyOcuppiedException.class)
    public ResponseEntity<Object> handleParkingSlotAlreadyOccupiedException(EntityAlreadyDeletedException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.GONE.value());
        body.put("error", "Gone");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4)); // Remove 'uri=' prefix

        return new ResponseEntity<>(body, HttpStatus.GONE);
    }
}
