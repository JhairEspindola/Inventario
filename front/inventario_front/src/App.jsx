import './App.css'
import { useEffect, useState } from 'react'
import Welcome from './pages/welcome'
import Landing from './pages/landing'
import Register from './pages/register'
import Padre from './pages/padre'
import Form from './pages/form'
import Hijo from './pages/hijo'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  const [products, setProducts] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem('inventario-products') || '[]')
    } catch {
      return []
    }
  })
  const [productDraft, setProductDraft] = useState(null)

  useEffect(() => {
    localStorage.setItem('inventario-products', JSON.stringify(products))
  }, [products])

  return (
    <BrowserRouter>
      <div className="app-shell">
        <Routes>
          <Route path="/" element={<Welcome />} />
          <Route path="/landing" element={<Landing />} />
          <Route path="/register" element={<Register />} />
          <Route path="/hijo" element={<Hijo onContinue={setProductDraft} />} />
          <Route path="/form" element={<Form product={productDraft} onSave={(product) => {
            setProducts((currentProducts) => [...currentProducts, {
              ...productDraft,
              id: `${Date.now()}-${Math.random().toString(36).slice(2)}`,
              stock: product.stock,
            }])
            setProductDraft(null)
          }} />} />
          <Route path="/padre" element={<Padre products={products} setProducts={setProducts} />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App
