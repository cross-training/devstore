package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;
import cloud.crosstraining.devstore.catalog.domain.Category;

public class CategoryMapper {
    public static CategoryMapper INSTANCE = new CategoryMapper();
    public CategoryEntity toEntity(Category source) {
        return CategoryEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public Category toDomain(CategoryEntity source) {
        return Category.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
