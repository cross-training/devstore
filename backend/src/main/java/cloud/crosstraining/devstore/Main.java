package cloud.crosstraining.devstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
//		loadEnvFromFileIfExists(args);
		SpringApplication.run(Main.class, args);
	}

//	private static void loadEnvFromFileIfExists(String[] args) {
//		String envFilePath = null;
//		for (int i = 0; i < args.length; i++) {
//			if (args[i].equals("--envFile") && i + 1 < args.length) {
//				envFilePath = args[i + 1];
//				break;
//			}
//		}
//		if (envFilePath == null) {
//			return;
//		}
//		Properties props = new Properties();
//		try (FileInputStream fis = new FileInputStream(envFilePath)) {
//			props.load(fis);
//			props.forEach((key, value) -> {
//				String envKey = key.toString();
//				String envValue = value.toString();
//				System.setProperty(envKey, envValue);
//			});
//		} catch (IOException e) {
//			System.err.println("Error loading environment variables from file: " + e.getMessage());
//		}
//	}
}
