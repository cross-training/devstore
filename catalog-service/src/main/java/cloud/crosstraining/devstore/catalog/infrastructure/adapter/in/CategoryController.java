package cloud.crosstraining.devstore.catalog.infrastructure.adapter.in;

import cloud.crosstraining.devstore.catalog.application.port.in.CategoryService;
import cloud.crosstraining.devstore.catalog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController extends Controller<Category, String> {

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }
}
