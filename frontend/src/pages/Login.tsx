import { useState } from 'react'
import { Link, useNavigate, useLocation } from 'react-router-dom'
import { login } from '../api/godownManagement'
import { useAuth } from '../context/AuthContext'
import type { UserLoginRequest } from '../api/godownManagement'
import './Auth.css'

function validateLogin(form: UserLoginRequest): string[] {
  const errors: string[] = []
  if (!form.contactNo?.trim()) errors.push('Contact number is required.')
  else if (!/^\d{10}$/.test(form.contactNo))
    errors.push('Contact number must be exactly 10 digits.')
  if (!form.password?.trim()) errors.push('Password is required.')
  return errors
}

export default function Login() {
  const navigate = useNavigate()
  const location = useLocation()
  const { login: setAuth } = useAuth()
  const [form, setForm] = useState<UserLoginRequest>({ contactNo: '', password: '' })
  const [errors, setErrors] = useState<string[]>([])
  const [submitError, setSubmitError] = useState('')
  const [loading, setLoading] = useState(false)

  const message = (location.state as { message?: string })?.message

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setForm((prev) => ({ ...prev, [name]: value }))
    setErrors([])
    setSubmitError('')
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    const validationErrors = validateLogin(form)
    if (validationErrors.length) {
      setErrors(validationErrors)
      return
    }
    setLoading(true)
    setSubmitError('')
    const { data, error } = await login(form)
    setLoading(false)
    if (error) {
      setSubmitError(error.message || 'Login failed.')
      return
    }
    if (data) {
      setAuth(data)
      navigate('/dashboard', { replace: true })
    }
  }

  return (
    <div className="auth-card">
      <h1>Login</h1>
      <p className="auth-subtitle">Sign in to manage your godowns.</p>
      {message && <p className="success-msg">{message}</p>}
      <form onSubmit={handleSubmit} className="auth-form">
        {errors.length > 0 && (
          <ul className="error-list">
            {errors.map((err, i) => (
              <li key={i}>{err}</li>
            ))}
          </ul>
        )}
        {submitError && <p className="error-msg">{submitError}</p>}
        <label>
          Contact number (10 digits)
          <input
            type="text"
            name="contactNo"
            value={form.contactNo}
            onChange={handleChange}
            placeholder="10-digit contact number"
            inputMode="numeric"
            maxLength={10}
            autoComplete="tel"
          />
        </label>
        <label>
          Password
          <input
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
            placeholder="Password"
            autoComplete="current-password"
          />
        </label>
        <button type="submit" className="btn-primary" disabled={loading}>
          {loading ? 'Logging in…' : 'Login'}
        </button>
      </form>
      <p className="auth-footer">
        Don&apos;t have an account? <Link to="/register">Register</Link>
      </p>
    </div>
  )
}
