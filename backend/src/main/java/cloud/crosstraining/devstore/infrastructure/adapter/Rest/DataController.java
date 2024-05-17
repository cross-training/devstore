package cloud.crosstraining.devstore.infrastructure.adapter.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import cloud.crosstraining.devstore.application.service.DataService;
import cloud.crosstraining.devstore.domain.entity.*;
import java.util.List;


@RestController
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return dataService.getCategories();
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return dataService.getRoles();
    }

    @GetMapping("/technologies")
    public List<Technology> getTechnologies() {
        return dataService.getTechnologies();
    }

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category) {
        dataService.addCategory(category);
    }

    @DeleteMapping("/categories/{categoryName}")
    public void removeCategory(@PathVariable String categoryName) {
        dataService.removeCategory(categoryName);
    }

    @PostMapping("/roles")
    public void addRole(@RequestBody Role role) {
        dataService.addRole(role);
    }

    @DeleteMapping("/roles/{roleName}")
    public void removeRole(@PathVariable String roleName) {
        dataService.removeRole(roleName);
    }
   
    @PostMapping("/roles/{roleName}/technologies")
    public void assignTechnologyToRole(@PathVariable String roleName, @RequestBody String technologyName) {
        dataService.assignTechnologyToRole(roleName, technologyName);
    }

    @DeleteMapping("/roles/{roleName}/technologies/{technologyName}")
    public void removeTechnologyFromRole(@PathVariable String roleName, @PathVariable String technologyName) {
        dataService.removeTechnologyFromRole(roleName, technologyName);
    }

    @PostMapping("/categories/{categoryName}/technologies")
    public void addTechnologyToCategory(@PathVariable String categoryName, @RequestBody Technology technology) {
        dataService.addTechnologyToCategory(categoryName, technology);
    }

    @DeleteMapping("/categories/{categoryName}/technologies/{technologyName}")
    public void removeTechnologyFromCategory(@PathVariable String categoryName, @PathVariable String technologyName) {
        dataService.removeTechnologyFromCategory(categoryName, technologyName);
    }
}
