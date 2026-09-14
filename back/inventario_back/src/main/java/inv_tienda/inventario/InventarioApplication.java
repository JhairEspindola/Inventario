package inv_tienda.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"inv_tienda.inventario.repositories"})
public class InventarioApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}
}