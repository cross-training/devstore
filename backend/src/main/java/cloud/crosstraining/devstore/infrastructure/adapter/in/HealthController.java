package cloud.crosstraining.devstore.infrastructure.adapter.in;

import cloud.crosstraining.devstore.application.port.in.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final HealthService service;

    public HealthController(HealthService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public String health() {
        return service.health();
    }
}
