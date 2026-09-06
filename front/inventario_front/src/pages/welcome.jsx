import Text from "../components/text";
import Header from "../components/header";
import Footer from "../components/footer";
import {Link} from "react-router-dom";
import "./welcome.css";

function Welcome() {
  return (
    <>
      <div className="header">
        <Header/>
      </div>
      <div className="welcome-container">
        <Text/>
      </div>
      <div className="containers">
        <section className="container">
          <h2>Bienvenido al Administrador de Inventario</h2>
          <p>
            Esta aplicación te permite gestionar y controlar el inventario de tu negocio de manera eficiente. 
            Puedes agregar, editar y eliminar productos, así como realizar un seguimiento de las existencias y generar informes.
          </p>
        </section>
        <section className="container2">
          <h2>Características principales</h2>
          <ul>
            <li>Agregar, editar y eliminar productos del inventario.</li>
            <li>Control de existencias y alertas de stock bajo.</li>
            <li>Generación de informes y estadísticas de ventas.</li>
            <li>Interfaz intuitiva y fácil de usar.</li>
          </ul>
          <section className="container3">
            <h2>¡Comienza a gestionar tu inventario hoy mismo!</h2>
            <p>
              Explora las funcionalidades de la aplicación y optimiza la gestión de tu inventario para mejorar la eficiencia de tu negocio.
            </p>
            <Link to="/landing">
              <button className="start-button">Comenzar</button>
            </Link>
          </section>
        </section>
      </div>
      <div className="footer">
        <Footer/>
      </div>
    </>
    
  );
}
export default Welcome;