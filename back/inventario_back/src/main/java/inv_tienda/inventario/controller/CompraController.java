package inv_tienda.inventario.controller;

import inv_tienda.inventario.DTO.CompraRequest;
import inv_tienda.inventario.DTO.CompraResponse;
import inv_tienda.inventario.services.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponse> create(@Valid @RequestBody CompraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compraService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CompraResponse>> findAll() {
        return ResponseEntity.ok(compraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponse> update(@PathVariable Long id, @Valid @RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
