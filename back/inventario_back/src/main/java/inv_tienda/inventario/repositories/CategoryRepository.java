package inv_tienda.inventario.repositories;

import inv_tienda.inventario.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameCategoryIgnoreCase(String name);

    Optional<Category> findByNameCategoryIgnoreCase(String name);
}