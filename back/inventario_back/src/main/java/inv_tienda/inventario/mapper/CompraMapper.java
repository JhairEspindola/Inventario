package inv_tienda.inventario.mapper;

import inv_tienda.inventario.DTO.CompraRequest;
import inv_tienda.inventario.DTO.CompraResponse;
import inv_tienda.inventario.entity.Category;
import inv_tienda.inventario.entity.Compra;
import org.springframework.stereotype.Component;

@Component
public class CompraMapper {

    public Compra toEntity(CompraRequest request, Category category) {
        Compra compra = Compra.builder()
                .category(category)
                .cantidad(request.cantidad())
                .precioUnitario(request.precioUnitario())
                .nota(request.nota())
                .build();
        compra.calculateTotal();
        return compra;
    }

    public void updateEntity(Compra compra, CompraRequest request, Category category) {
        compra.setCategory(category);
        compra.setCantidad(request.cantidad());
        compra.setPrecioUnitario(request.precioUnitario());
        compra.setNota(request.nota());
        compra.calculateTotal();
    }

    public CompraResponse toResponse(Compra compra) {
        return CompraResponse.builder()
                .id(compra.getIdCompra())
                .idCategoria(compra.getCategory().getIdCategory())
                .nombreCategoria(compra.getCategory().getNameCategory())
                .cantidad(compra.getCantidad())
                .precioUnitario(compra.getPrecioUnitario())
                .total(compra.getTotal())
                .nota(compra.getNota())
                .createdAt(compra.getCreateAt())
                .updatedAt(compra.getUpdateAt())
                .build();
    }
}
