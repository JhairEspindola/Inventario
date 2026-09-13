import './components.css';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header className="site-header">
      <div className="header-inner">
        <Link className="brand" to="/">
          <span className="brand-mark" aria-hidden="true">+</span>
          <span className="brand-text">Inventario Verduleria</span>
        </Link>
        <nav className="site-nav" aria-label="Navegación principal">
          <Link className="nav-link" to="/">Inicio</Link>
          <Link className="nav-link" to="/landing">Funcionalidades</Link>
        </nav>
      </div>
    </header>
  );
}

export default Header;