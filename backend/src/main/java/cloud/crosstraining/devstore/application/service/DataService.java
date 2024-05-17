package cloud.crosstraining.devstore.application.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cloud.crosstraining.devstore.domain.entity.*;
import cloud.crosstraining.devstore.domain.port.DataRepository;

import java.util.List;

@Service
public class DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Category> getCategories() {
        return dataRepository.getCategories();
    }

    public List<Role> getRoles() {
        return dataRepository.getRoles();
    }

    public List<Technology> getTechnologies() {
        return dataRepository.getTechnologies();
    }

    public void addCategory(Category category) {
        dataRepository.addCategory(category);
    }

    public void removeCategory(String categoryName) {
        dataRepository.removeCategory(categoryName);
    }

    public void addRole(Role role) {
        dataRepository.addRole(role);
    }

    public void removeRole(String roleName) {
        dataRepository.removeRole(roleName);
    }

    public void assignTechnologyToRole(String roleName, String technologyName) {
        dataRepository.assignTechnologyToRole(roleName, technologyName);
    }

    public void removeTechnologyFromRole(String roleName, String technologyName) {
        dataRepository.removeTechnologyFromRole(roleName, technologyName);
    }

    public void addTechnologyToCategory(String categoryName, Technology technology) {
        dataRepository.addTechnologyToCategory(categoryName, technology);
    }

    public void removeTechnologyFromCategory(String categoryName, String technologyName) {
        dataRepository.removeTechnologyFromCategory(categoryName, technologyName);
    }
}
