package cloud.crosstraining.devstore.application.service;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    
    public String health() {
        return "Service is up and running!";
    }
}
