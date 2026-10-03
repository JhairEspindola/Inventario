package inv_tienda.inventario.controller;

import inv_tienda.inventario.DTO.VentaRequest;
import inv_tienda.inventario.DTO.VentaResponse;
import inv_tienda.inventario.services.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponse> create(@Valid @RequestBody VentaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponse>> findAll() {
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> update(@PathVariable Long id, @Valid @RequestBody VentaRequest request) {
        return ResponseEntity.ok(ventaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
