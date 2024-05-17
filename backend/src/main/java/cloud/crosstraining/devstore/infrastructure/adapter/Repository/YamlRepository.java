package cloud.crosstraining.devstore.infrastructure.adapter.Repository;
import cloud.crosstraining.devstore.domain.entity.*;
import cloud.crosstraining.devstore.domain.port.DataRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
public class YamlRepository implements DataRepository {
    private List<Category> categories;
    private List<Role> roles;

    public YamlRepository(InputStream inputStream) {
        loadYamlData(inputStream);
    }
    
    private void loadYamlData(InputStream inputStream) {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlData = yaml.load(inputStream);
        categories = (List<Category>) yamlData.get("categories");
        roles = (List<Role>) yamlData.get("roles");
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

   @Override
    public List<Technology> getTechnologies() {
        return categories.stream()
                        .flatMap(category -> category.getTechnologies().stream())
                        .collect(Collectors.toList());
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public void removeCategory(Category category) {
        categories.remove(category);
    }

    @Override
    public void removeRole(Role role) {
        roles.remove(role);
    }

    @Override
    public void assignTechnologyToRole(String roleName, String technologyName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                role.getTechnologies().add(technologyName);
                return;
            }
        }
    }

    @Override
    public void removeTechnologyFromRole(String roleName, String technologyName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                role.getTechnologies().remove(technologyName);
                return;
            }
        }
    }

    @Override
    public void addTechnologyToCategory(String categoryName, Technology technology) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                category.getTechnologies().add(technology);
                return;
            }
        }
    }

    @Override
    public void removeTechnologyFromCategory(String categoryName, String technologyName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                Iterator<Technology> iterator = category.getTechnologies().iterator();
                while (iterator.hasNext()) {
                    Technology currentTech = iterator.next();
                    if (currentTech.getName().equals(technologyName)) {
                        iterator.remove();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void removeCategory(String categoryName) {
        Category categoryToRemove = null;
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                categoryToRemove = category;
                break;
            }
        }
        if (categoryToRemove != null) {
            categories.remove(categoryToRemove);
        }
    }
    
    @Override
    public void removeRole(String roleName) {
        Role roleToRemove = null;
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                roleToRemove = role;
                break;
            }
        }
        if (roleToRemove != null) {
            roles.remove(roleToRemove);
        }
    }
    
}
