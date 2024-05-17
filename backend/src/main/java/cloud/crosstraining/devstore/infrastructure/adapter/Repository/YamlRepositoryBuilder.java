package cloud.crosstraining.devstore.infrastructure.adapter.Repository;
import java.io.InputStream;
import java.util.Objects;

public class YamlRepositoryBuilder {
    private InputStream inputStream;

    public YamlRepositoryBuilder withInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public YamlRepository build() {
        Objects.requireNonNull(inputStream, "Input stream must not be null");
        return new YamlRepository(inputStream);
    }
}
