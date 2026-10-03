package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.CompraRequest;
import inv_tienda.inventario.DTO.CompraResponse;

import java.util.List;

public interface CompraService {

    CompraResponse create(CompraRequest request);

    List<CompraResponse> findAll();

    CompraResponse findById(Long id);

    CompraResponse update(Long id, CompraRequest request);

    void delete(Long id);
}
