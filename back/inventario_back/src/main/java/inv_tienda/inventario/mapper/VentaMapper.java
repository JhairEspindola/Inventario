package inv_tienda.inventario.mapper;

import inv_tienda.inventario.DTO.VentaRequest;
import inv_tienda.inventario.DTO.VentaResponse;
import inv_tienda.inventario.entity.Category;
import inv_tienda.inventario.entity.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {

    public Venta toEntity(VentaRequest request, Category category) {
        Venta venta = Venta.builder()
                .category(category)
                .cantidad(request.cantidad())
                .precioUnitario(request.precioUnitario())
                .nota(request.nota())
                .build();
        venta.calculateTotal();
        return venta;
    }

    public void updateEntity(Venta venta, VentaRequest request, Category category) {
        venta.setCategory(category);
        venta.setCantidad(request.cantidad());
        venta.setPrecioUnitario(request.precioUnitario());
        venta.setNota(request.nota());
        venta.calculateTotal();
    }

    public VentaResponse toResponse(Venta venta) {
        return VentaResponse.builder()
                .id(venta.getIdVenta())
                .idCategoria(venta.getCategory().getIdCategory())
                .nombreCategoria(venta.getCategory().getNameCategory())
                .cantidad(venta.getCantidad())
                .precioUnitario(venta.getPrecioUnitario())
                .total(venta.getTotal())
                .nota(venta.getNota())
                .createdAt(venta.getCreateAt())
                .updatedAt(venta.getUpdateAt())
                .build();
    }
}
