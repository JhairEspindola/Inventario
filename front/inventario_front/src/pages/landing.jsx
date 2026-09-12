import Header from "../components/header";
import Footer from "../components/footer";
import "./landing.css";
import Imagen from "../assets/images.jpg"

function Landing() {
  return (
    <>
      <Header/>
      <main className="landing-page">
        <section className="landing-intro">
          <p className="landing-kicker">FUNCIONALIDADES</p>
          <h1>Una vista más clara de todo lo que tienes.</h1>
          <p>Gestiona y controla el inventario de tu negocio de manera eficiente, sin complicaciones.</p>
        </section>
        <section className="landing-content">
          <article className="landing-feature">
            <h2>Productos siempre actualizados</h2>
            <p>Mantén tu inventario al día agregando nuevos productos, editando la información existente y retirando lo que ya no está disponible.</p>
          </article>
          <div className="image-container">
            <img src={Imagen} alt="Productos frescos organizados en un inventario" className="landing-image"/>
          </div>
          <article className="landing-feature">
            <h2>Control de existencias</h2>
            <p>Monitorea las existencias y detecta con anticipación los productos que están cerca de agotarse para planificar tus compras.</p>
          </article>
          <article className="landing-feature">
            <h2>Diseñado para tu día a día</h2>
            <p>Una interfaz simple y ordenada para que encontrar la información importante sea rápido, incluso desde dispositivos pequeños.</p>
          </article>
        </section>
      </main>
      <Footer/>
    </>
  );
}
export default Landing;