import { Link } from "react-router-dom";
import Header from "../components/header";
import Footer from "../components/footer";
import "./padre.css";

function Padre({ products, setProducts }) {
  const availableCount = products.filter((product) => product.stock > 0).length;
  const outOfStockCount = products.length - availableCount;

  const sellUnit = (productId) => {
    setProducts((currentProducts) => currentProducts.map((product) =>
      product.id === productId
        ? { ...product, stock: Math.max(0, product.stock - 1) }
        : product
    ));
  };

  return (
    <>
      <Header />
      <main className="padre-page">
        <section className="padre-heading">
          <div>
            <p className="padre-kicker">INVENTARIO</p>
            <h1>Productos registrados</h1>
            <p>Consulta rápidamente la información y disponibilidad de tus productos.</p>
          </div>
          <Link className="padre-add-link" to="/hijo">Registrar producto</Link>
        </section>

        <section className="padre-summary" aria-label="Resumen del inventario">
          <article className="summary-card">
            <span className="summary-label">Total de productos</span>
            <strong>{products.length}</strong>
          </article>
          <article className="summary-card">
            <span className="summary-label">Disponibles</span>
            <strong>{availableCount}</strong>
          </article>
          <article className="summary-card summary-card-warning">
            <span className="summary-label">Agotados</span>
            <strong>{outOfStockCount}</strong>
          </article>
        </section>

        <section className="product-section" aria-labelledby="product-list-title">
          <div className="product-section-heading">
            <div>
              <h2 id="product-list-title">Listado de productos</h2>
              <p>Información detallada de cada artículo en tu inventario.</p>
            </div>
            <span className="product-count">{products.length} {products.length === 1 ? "artículo" : "artículos"}</span>
          </div>
          {products.length > 0 ? (
            <div className="product-grid">
              {products.map((product) => {
                const isAvailable = product.stock > 0;
                return (
                  <article className={`herencia ${isAvailable ? "" : "herencia-agotado"}`} key={product.id}>
                    <div className="product-card-header">
                      <div>
                        <p className="product-type">{product.tipo}</p>
                        <h3>{product.nombre}</h3>
                      </div>
                      <span className="product-status">{isAvailable ? "Disponible" : "Agotado"}</span>
                    </div>
                    <p className="product-price">${product.precio.toFixed(2)}</p>
                    <dl className="product-details">
                      <div><dt>Marca</dt><dd>{product.marca}</dd></div>
                      <div><dt>Peso</dt><dd>{product.peso}</dd></div>
                      <div>
                        <dt>Stock</dt>
                        <dd>{product.stock} {product.stock === 1 ? "unidad" : "unidades"}</dd>
                        <button className="stock-button" type="button" onClick={() => sellUnit(product.id)} disabled={!isAvailable}>
                          {isAvailable ? "Vender unidad" : "Agotado"}
                        </button>
                      </div>
                    </dl>
                    {product.descripcion && <p className="product-description">{product.descripcion}</p>}
                  </article>
                );
              })}
            </div>
          ) : (
            <div className="product-empty-state">
              <p>Aún no hay productos registrados.</p>
              <Link to="/hijo">Registrar el primer producto</Link>
            </div>
          )}
        </section>
      </main>
      <Footer />
    </>
  );
}

export default Padre;