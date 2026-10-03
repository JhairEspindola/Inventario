package inv_tienda.inventario.repositories;

import inv_tienda.inventario.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    boolean existsByCategory_IdCategory(Long idCategoria);
}
