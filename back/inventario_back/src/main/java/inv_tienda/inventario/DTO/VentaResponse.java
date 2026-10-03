package inv_tienda.inventario.DTO;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record VentaResponse(
        Long id,
        Long idCategoria,
        String nombreCategoria,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal total,
        String nota,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
