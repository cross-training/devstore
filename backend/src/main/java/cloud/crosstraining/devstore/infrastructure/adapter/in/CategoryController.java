package cloud.crosstraining.devstore.infrastructure.adapter.in;

import cloud.crosstraining.devstore.application.port.in.CategoryService;
import cloud.crosstraining.devstore.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends Controller<Category,Long> {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }
}