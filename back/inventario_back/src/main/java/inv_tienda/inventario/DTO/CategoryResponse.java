package inv_tienda.inventario.DTO;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record CategoryResponse(long id,
                               String name,
                               String description,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
}
