package cloud.crosstraining.devstore.domain.port;
import java.util.List;
import cloud.crosstraining.devstore.domain.entity.*;

public interface DataRepository {
    List<Category> getCategories();
    List<Role> getRoles();
    List<Technology> getTechnologies();

    void addCategory(Category category);
    void removeCategory(Category category);

    void addTechnologyToCategory(String categoryName, Technology technologyName);
    void removeTechnologyFromCategory(String categoryName, String technologyName);

    void addRole(Role role);
    void removeRole(Role role);


    void assignTechnologyToRole(String roleName, String technologyName);
    void removeTechnologyFromRole(String roleName, String technologyName);
    void removeCategory(String categoryName);
    void removeRole(String roleName); 
}
