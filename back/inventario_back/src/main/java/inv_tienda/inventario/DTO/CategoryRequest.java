package inv_tienda.inventario.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest (
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre debe tener menos de 80 caracteres")
    String name,
    @Size(max= 250, message = "La descripcion debe ser menor de 250 caracteres")
    String description
    )
    {

    }
