import Header from "../components/header";
import Footer from "../components/footer";
import "./landing.css";
import Imagen from "../assets/images.jpg"

function Landing() {
  return (
    <body>
      <div className="header">
        <Header/>
      </div>
      <div className="landing-container">
        <p className="landing-description">
          Gestiona y controla el inventario de tu negocio de manera eficiente.
        </p>
      </div>
      <div className="landing-features">
        <section className="landing-feature">
          <h2>Agregar, editar y eliminar productos</h2>
            <p> 
            Mantén tu inventario actualizado agregando nuevos productos, editando la información existente y eliminando aquellos que ya no estén disponibles.
            </p>
        </section>
      </div>
      <div className="image-container">
        <img src={Imagen} alt="Imagen de inventario" className="landing-image"/>
      </div>
      <div className="landing-features">
        <section className="landing-feature">
          <h2>Control de existencias y alertas de stock bajo</h2>
            <p>
              Monitorea las existencias en tiempo real y recibe notificaciones cuando el stock de algún producto esté por agotarse.
            </p>
        </section>
      </div>
      <div className="footer">
        <Footer/>
      </div>
    </body>
  );
}
export default Landing;