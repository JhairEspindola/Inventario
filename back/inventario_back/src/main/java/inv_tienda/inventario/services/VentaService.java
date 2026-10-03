package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.VentaRequest;
import inv_tienda.inventario.DTO.VentaResponse;

import java.util.List;

public interface VentaService {

    VentaResponse create(VentaRequest request);

    List<VentaResponse> findAll();

    VentaResponse findById(Long id);

    VentaResponse update(Long id, VentaRequest request);

    void delete(Long id);
}
