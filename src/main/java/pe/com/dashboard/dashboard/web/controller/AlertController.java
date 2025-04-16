package pe.com.dashboard.dashboard.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dashboard.dashboard.domain.dto.AlertaDTO;
import pe.com.dashboard.dashboard.domain.service.AlertService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;


    @GetMapping
    public ResponseEntity<List<AlertaDTO>> getAllAlerts() {
        try {
            List<AlertaDTO> alertas = alertService.getAll();
            return new ResponseEntity<>(alertas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> getAlertById(@PathVariable Integer id) {
        try {
            AlertaDTO alerta = alertService.getById(id);
            if (alerta != null) {
                return new ResponseEntity<>(alerta, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> createAlert(@RequestBody AlertaDTO alertaDTO) {
        try {
            AlertaDTO savedAlert = alertService.save(alertaDTO);
            return new ResponseEntity<>(savedAlert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> updateAlert(@PathVariable Integer id, @RequestBody AlertaDTO alertaDTO) {
        try {
            AlertaDTO updatedAlert = alertService.update(id, alertaDTO);
            if (updatedAlert != null) {
                return new ResponseEntity<>(updatedAlert, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Integer id) {
        try {
            alertService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertaDTO>> getAlertsByUserId(@PathVariable Integer userId) {
        try {
            List<AlertaDTO> alerts = alertService.getByUserId(userId);
            return new ResponseEntity<>(alerts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<AlertaDTO>> getAlertsByDateRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        try {
            List<AlertaDTO> alerts = alertService.getByDateRange(start, end);
            return new ResponseEntity<>(alerts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
