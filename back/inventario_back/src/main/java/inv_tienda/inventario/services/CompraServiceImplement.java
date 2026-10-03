package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.CompraRequest;
import inv_tienda.inventario.DTO.CompraResponse;
import inv_tienda.inventario.Exception.ResourceNotFoundException;
import inv_tienda.inventario.entity.Category;
import inv_tienda.inventario.entity.Compra;
import inv_tienda.inventario.mapper.CompraMapper;
import inv_tienda.inventario.repositories.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraServiceImplement implements CompraService {

    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public CompraResponse create(CompraRequest request) {
        Category category = categoryService.getEntity(request.idCategoria());
        Compra compra = compraMapper.toEntity(request, category);
        Compra saved = compraRepository.save(compra);
        categoryService.increaseStock(category.getIdCategory(), request.cantidad());
        return compraMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompraResponse> findAll() {
        return compraRepository.findAll().stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompraResponse findById(Long id) {
        return compraMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional
    public CompraResponse update(Long id, CompraRequest request) {
        Compra compra = getEntity(id);
        Long previousCategoryId = compra.getCategory().getIdCategory();
        int previousCantidad = compra.getCantidad();
        Category newCategory = categoryService.getEntity(request.idCategoria());

        if (previousCategoryId.equals(request.idCategoria())) {
            categoryService.applyStockDelta(previousCategoryId, request.cantidad() - previousCantidad);
        } else {
            categoryService.decreaseStock(previousCategoryId, previousCantidad);
            categoryService.increaseStock(newCategory.getIdCategory(), request.cantidad());
        }

        compraMapper.updateEntity(compra, request, newCategory);
        return compraMapper.toResponse(compraRepository.save(compra));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Compra compra = getEntity(id);
        categoryService.decreaseStock(compra.getCategory().getIdCategory(), compra.getCantidad());
        compraRepository.delete(compra);
    }

    private Compra getEntity(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada con id " + id));
    }
}
