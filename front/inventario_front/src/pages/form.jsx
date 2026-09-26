import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Header from "../components/header";
import Footer from "../components/footer";
import "./form.css";

function Form({ product, onSave }) {
  const [units, setUnits] = useState(1);
  const navigate = useNavigate();

  const handleSave = () => {
    onSave({ stock: units });
    navigate("/padre");
  };

  return (
    <>
      <Header />
      <main className="form-page">
        <section className="form-shell">
          <div className="form-heading">
            <p className="form-kicker">INVENTARIO / EXISTENCIAS</p>
            <h1>Agregar unidades</h1>
            <p>Revisa la información y define cuántas unidades quieres registrar.</p>
          </div>

          {product ? (
            <div className="stock-panel">
              <p className="stock-product-type">{product.tipo}</p>
              <h2 className="stock-product-name">{product.nombre}</h2>
              <dl className="stock-product-details">
                <div><dt>Marca o proveedor</dt><dd>{product.marca}</dd></div>
                <div><dt>Precio por unidad</dt><dd>${product.precio.toFixed(2)}</dd></div>
                <div><dt>Peso o presentación</dt><dd>{product.peso}</dd></div>
                {product.descripcion && <div className="stock-description"><dt>Descripción</dt><dd>{product.descripcion}</dd></div>}
              </dl>

              <span className="stock-quantity-label">Unidades iniciales</span>
              <div className="stock-quantity-controls" aria-label="Cantidad inicial">
                <button className="stock-adjust-button" type="button" aria-label="Quitar una unidad" onClick={() => setUnits((quantity) => Math.max(1, quantity - 1))} disabled={units <= 1}>−</button>
                <output className="stock-quantity-value" aria-live="polite">{units}</output>
                <button className="stock-adjust-button" type="button" aria-label="Añadir una unidad" onClick={() => setUnits((quantity) => quantity + 1)}>+</button>
              </div>
              <button className="stock-add-one" type="button" onClick={() => setUnits((quantity) => quantity + 1)}>Añadir una unidad</button>

              <div className="stock-form-actions">
                <Link className="stock-back-link" to="/hijo">Editar producto</Link>
                <button className="stock-save-button" type="button" onClick={handleSave}>Guardar en inventario</button>
              </div>
            </div>
          ) : (
            <div className="stock-empty-state">
              <p>Primero registra los datos del producto.</p>
              <Link className="stock-back-link" to="/hijo">Registrar producto</Link>
            </div>
          )}
        </section>
      </main>
      <Footer />
    </>
  );
}

export default Form;