package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.VentaRequest;
import inv_tienda.inventario.DTO.VentaResponse;
import inv_tienda.inventario.Exception.ResourceNotFoundException;
import inv_tienda.inventario.entity.Category;
import inv_tienda.inventario.entity.Venta;
import inv_tienda.inventario.mapper.VentaMapper;
import inv_tienda.inventario.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImplement implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public VentaResponse create(VentaRequest request) {
        Category category = categoryService.getEntity(request.idCategoria());
        categoryService.decreaseStock(category.getIdCategory(), request.cantidad());
        Venta venta = ventaMapper.toEntity(request, category);
        Venta saved = ventaRepository.save(venta);
        return ventaMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponse> findAll() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponse findById(Long id) {
        return ventaMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional
    public VentaResponse update(Long id, VentaRequest request) {
        Venta venta = getEntity(id);
        Long previousCategoryId = venta.getCategory().getIdCategory();
        int previousCantidad = venta.getCantidad();
        Category newCategory = categoryService.getEntity(request.idCategoria());

        if (previousCategoryId.equals(request.idCategoria())) {
            categoryService.applyStockDelta(previousCategoryId, previousCantidad - request.cantidad());
        } else {
            categoryService.increaseStock(previousCategoryId, previousCantidad);
            categoryService.decreaseStock(newCategory.getIdCategory(), request.cantidad());
        }

        ventaMapper.updateEntity(venta, request, newCategory);
        return ventaMapper.toResponse(ventaRepository.save(venta));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Venta venta = getEntity(id);
        categoryService.increaseStock(venta.getCategory().getIdCategory(), venta.getCantidad());
        ventaRepository.delete(venta);
    }

    private Venta getEntity(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id " + id));
    }
}
