package cloud.crosstraining.devstore.infrastructure.configuration;
import cloud.crosstraining.devstore.application.service.HealthService;
import cloud.crosstraining.devstore.domain.port.DataRepository;
import cloud.crosstraining.devstore.infrastructure.adapter.Repository.YamlRepository;
import java.io.IOException;
import java.io.InputStream;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import cloud.crosstraining.devstore.domain.port.DataRepository;



@Configuration
@EnableSwagger2
public class WebConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Value("classpath:/src/main/resources/data.yml")
    private Resource yamlFile;

    @Bean
    public DataRepository dataRepository() {
        try {
            InputStream inputStream = yamlFile.getInputStream();
            // Aquí puedes agregar lógica para determinar qué implementación de repositorio utilizar
            return new YamlRepository(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading YAML file", e);
        }
    }

   

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Configura las propiedades de la conexión de la base de datos
        dataSource.setDriverClassName("org.h2.Driver"); // Cambia esto según el driver de tu base de datos
        dataSource.setUrl("jdbc:h2:mem:testdb"); // Cambia esto según la URL de tu base de datos
        dataSource.setUsername("sa"); // Cambia esto según el usuario de tu base de datos
        dataSource.setPassword(""); // Cambia esto según la contraseña de tu base de datos
        return dataSource;
    }
}
