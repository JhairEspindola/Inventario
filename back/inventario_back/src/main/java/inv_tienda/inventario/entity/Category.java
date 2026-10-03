package inv_tienda.inventario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(name = "nombre", nullable = false, unique = true, length = 80)
    private String nameCategory;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "stock", nullable = false)
    @Builder.Default
    private Integer stock = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime currentDate = LocalDateTime.now();
        this.createAt = currentDate;
        this.updateAt = currentDate;
        if (this.stock == null) {
            this.stock = 0;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
