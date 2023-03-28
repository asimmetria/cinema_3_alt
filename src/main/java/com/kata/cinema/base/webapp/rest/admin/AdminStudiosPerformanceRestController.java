package com.kata.cinema.base.webapp.rest.admin;

import com.kata.cinema.base.exception.NoSuchConnectionException;
import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;
import com.kata.cinema.base.models.entitys.ProductionStudiosMovie;
import com.kata.cinema.base.models.entitys.StudiosPerformance;
import com.kata.cinema.base.webapp.facade.performance.StudiosPerformanceServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminStudiosPerformanceRestController {

    private final StudiosPerformanceServiceFacade performanceServiceFacade;
    private final StudiosPerformance performance;

    @PostMapping("/performance/")
    public ResponseEntity<Void> createPerformance(@RequestParam String name) {
        performanceServiceFacade.save(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/performance/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable("id") Long id) {

        ProductionStudiosMovie movie = (ProductionStudiosMovie) performance.getMovies();

        if (performance.getMovies().size() > 0) {
            throw new NoSuchConnectionException();
        }

        performanceServiceFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/performance/{id}")
    public ResponseEntity<Void> updatePerformance(@PathVariable("id") Long id,
                                                  @RequestParam("name") String name) {
        performanceServiceFacade.update(id, name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/performance")
    public ResponseEntity<List<PerformanceResponseDto>> getPerformance() {
        List<PerformanceResponseDto> dto = performanceServiceFacade.getPerformance();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
