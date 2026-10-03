package inv_tienda.inventario.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VentaRequest(
        @NotNull(message = "La categoria es obligatoria")
        Long idCategoria,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        Integer cantidad,

        @DecimalMin(value = "0.0", message = "El precio unitario no puede ser negativo")
        BigDecimal precioUnitario,

        @Size(max = 250, message = "La nota debe tener menos de 250 caracteres")
        String nota
) {
}
