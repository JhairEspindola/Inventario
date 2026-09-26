import { useNavigate } from "react-router-dom";
import Header from "../components/header";
import Footer from "../components/footer";
import "./hijo.css";

function Hijo({ onContinue }) {
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const values = new FormData(event.currentTarget);
    onContinue({
      nombre: values.get("nombre").trim(),
      tipo: values.get("tipo"),
      marca: values.get("marca").trim(),
      precio: Number(values.get("precio")),
      peso: values.get("peso").trim(),
      descripcion: values.get("descripcion").trim(),
    });
    navigate("/form");
  };

  return (
    <>
      <Header />
      <main className="hijo-page">
        <section className="hijo-shell">
          <div className="hijo-heading">
            <p className="hijo-kicker">INVENTARIO / NUEVO PRODUCTO</p>
            <h1>Registrar producto</h1>
            <p>Agrega los datos del producto. En el siguiente paso podrás indicar cuántas unidades ingresan al inventario.</p>
          </div>

          <form className="hijo-form" onSubmit={handleSubmit}>
            <div className="hijo-fields">
              <div className="hijo-field hijo-field-full">
                <label htmlFor="nombre">Nombre del producto *</label>
                <input id="nombre" name="nombre" type="text" minLength="2" placeholder="Ej. Manzana roja" required />
              </div>
              <div className="hijo-field">
                <label htmlFor="tipo">Tipo de producto *</label>
                <input id="tipo" name="tipo" type="text" placeholder="Ej. Fruta" required />
              </div>
              <div className="hijo-field">
                <label htmlFor="marca">Marca o proveedor *</label>
                <input id="marca" name="marca" type="text" placeholder="Ej. Productores locales" required />
              </div>
              <div className="hijo-field">
                <label htmlFor="precio">Precio por unidad *</label>
                <input id="precio" name="precio" type="number" min="0" step="0.01" placeholder="0.00" required />
              </div>
              <div className="hijo-field">
                <label htmlFor="peso">Peso o presentación *</label>
                <input id="peso" name="peso" type="text" placeholder="Ej. 1 kg" required />
              </div>
              <div className="hijo-field hijo-field-full">
                <label htmlFor="descripcion">Descripción</label>
                <textarea id="descripcion" name="descripcion" placeholder="Detalles adicionales del producto" />
              </div>
            </div>
            <div className="hijo-actions">
              <button className="hijo-submit" type="submit">Continuar a unidades</button>
            </div>
          </form>
        </section>
      </main>
      <Footer />
    </>
  );
}

export default Hijo;