package inv_tienda.inventario.DTO;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryResponse(
        Long id,
        String nombre,
        String descripcion,
        Integer stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
