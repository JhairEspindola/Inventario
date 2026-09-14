import './App.css'
import Welcome from './pages/welcome'
import Landing from './pages/landing'
import Register from './pages/register'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <div className="app-shell">
        <Routes>
          <Route path="/" element={<Welcome />} />
          <Route path="/landing" element={<Landing />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App
