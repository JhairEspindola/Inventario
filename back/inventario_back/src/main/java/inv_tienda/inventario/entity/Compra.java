package inv_tienda.inventario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "nota", length = 250)
    private String nota;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createAt = now;
        this.updateAt = now;
        calculateTotal();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
        calculateTotal();
    }

    public void calculateTotal() {
        if (this.precioUnitario != null && this.cantidad != null) {
            this.total = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
        }
    }
}
