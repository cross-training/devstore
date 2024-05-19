package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Product,Long,ProductEntity> implements ProductRepository {

    private final CategoryRepositoryImpl categoryRepository;

    @Autowired
    public ProductRepositoryImpl(
            JpaProductRepository jpaProductRepository,
            EntityManagerFactory entityManager,
            CategoryRepositoryImpl categoryRepository
    ) {
        super(jpaProductRepository,entityManager);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product findById(Long id) {
        ProductEntity entity = _findById(id);
        if (entity!= null) {
            // force get children categories
            entity.getCategories().size();
        }
        return toDomain(entity);
    }

    @Override
    public List<Product> findAll(FindAllArgs args) {
        List<ProductEntity> entities = _findAll(args);
        if(Arrays.stream(args.getIncludes()).anyMatch(p -> p.toLowerCase() == "categories")) {
            entities.stream().forEach(p-> p.getCategories().size());
        }
        return entities.stream().map(this::toDomain).toList();
    }



//    @Override
//    public Product save(Product entity) {
//        List<CategoryEntity> categories = categoryRepository.getCategoryByNames(entity.getCategories());
//        ProductEntity current = entity.getId()!=null?jpaProductRepository.findById(entity.getId()).orElse(null):null;
//        if (producto != null) {
//            // Acceso a las categorías para asegurarse de que se cargan debido a la relación LAZY
//            producto.getCategorias().size();  // Esto fuerza la carga de las categorías
//        }
//
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            Categoria categoria1 = categories.stream().findFirst(p-> p.getId )
//
//            // Si no existe la categoría, puedes manejar el caso apropiadamente
//            if (categoria1 == null) {
//                throw new RuntimeException("Categoría no encontrada");
//            }
//
//            // Supongamos que otra categoría ya existe con ID 2
//            Long categoriaId2 = 2L;
//            Categoria categoria2 = em.find(Categoria.class, categoriaId2);
//
//            if (categoria2 == null) {
//                throw new RuntimeException("Categoría no encontrada");
//            }
//
//            // Crear un nuevo producto
//            Producto producto = new Producto();
//            producto.setNombre("Nuevo Producto");
//
//            // Asociar el producto con las categorías existentes
//            producto.getCategorias().add(categoria1);
//            producto.getCategorias().add(categoria2);
//
//            // Persistir el producto (las categorías ya existen y están gestionadas por JPA)
//            em.persist(producto);
//
//            // Confirmar la transacción
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//
//        return toDomain(jpaProductRepository.save(toEntity(entity)));
//    }

    @Override
    protected Product toDomain(ProductEntity entity) {
        Product product =  new Product(entity.getId(),entity.getCode(), entity.getName(), entity.getPrice(), entity.getDescription(), new ArrayList<String>(), entity.getUrl());
        product.getCategories().addAll(entity.getCategories().stream().map(p-> p.getName()).collect(Collectors.toList()));
        return product;
    }

    @Override
    protected ProductEntity toEntity(Product product) {
        throw new RuntimeException("Method excluded");
//        return new ProductEntity(product.getId(), product.getCode(), product.getName(), product.getPrice(), product.getDescription(), product.getCategories(), product.getUrl());
    }
}
