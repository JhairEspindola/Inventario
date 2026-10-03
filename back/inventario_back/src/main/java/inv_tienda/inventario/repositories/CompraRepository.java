package inv_tienda.inventario.repositories;

import inv_tienda.inventario.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    boolean existsByCategory_IdCategory(Long idCategoria);
}
