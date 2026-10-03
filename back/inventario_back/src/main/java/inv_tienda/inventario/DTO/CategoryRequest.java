package inv_tienda.inventario.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 80, message = "El nombre debe tener menos de 80 caracteres")
        String nombre,

        @Size(max = 250, message = "La descripcion debe ser menor de 250 caracteres")
        String descripcion,

        @Min(value = 0, message = "El stock inicial no puede ser negativo")
        Integer stock
) {
}
