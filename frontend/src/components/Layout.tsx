import { Outlet } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { Link } from 'react-router-dom'
import './Layout.css'

export default function Layout() {
  const { isAuthenticated, user, logout } = useAuth()

  return (
    <div className="layout">
      <header className="header">
        <Link to="/" className="brand">
          Godown Management
        </Link>
        <nav>
          {!isAuthenticated ? (
            <>
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </>
          ) : (
            <>
              <Link to="/dashboard">Dashboard</Link>
              <Link to="/add-items">Add Items</Link>
              <span className="user">{user?.userName}</span>
              <button type="button" onClick={logout} className="btn-link">
                Logout
              </button>
            </>
          )}
        </nav>
      </header>
      <main className="main">
        <Outlet />
      </main>
    </div>
  )
}
