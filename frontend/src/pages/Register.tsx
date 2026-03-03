import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { register } from '../api/godownManagement'
import type { UserRegisterRequest } from '../api/godownManagement'
import './Auth.css'

const USERNAME_MIN = 6
const USERNAME_MAX = 12
const PASSWORD_MIN = 6
const PASSWORD_MAX = 12
const CONTACT_LEN = 10

function validateRegister(form: UserRegisterRequest): string[] {
  const errors: string[] = []
  if (!form.userName?.trim()) errors.push('User name is required.')
  else if (form.userName.length < USERNAME_MIN || form.userName.length > USERNAME_MAX)
    errors.push(`User name should be ${USERNAME_MIN}–${USERNAME_MAX} characters.`)
  if (!form.password?.trim()) errors.push('Password is required.')
  else if (form.password.length < PASSWORD_MIN || form.password.length > PASSWORD_MAX)
    errors.push(`Password should be ${PASSWORD_MIN}–${PASSWORD_MAX} characters.`)
  if (!form.contactNo?.trim()) errors.push('Contact number is required.')
  else if (!/^\d{10}$/.test(form.contactNo))
    errors.push('Contact number must be exactly 10 digits.')
  if (!form.address?.trim()) errors.push('Address is required.')
  return errors
}

export default function Register() {
  const navigate = useNavigate()
  const [form, setForm] = useState<UserRegisterRequest>({
    userName: '',
    password: '',
    contactNo: '',
    address: '',
  })
  const [errors, setErrors] = useState<string[]>([])
  const [submitError, setSubmitError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target
    setForm((prev) => ({ ...prev, [name]: value }))
    setErrors([])
    setSubmitError('')
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    const validationErrors = validateRegister(form)
    if (validationErrors.length) {
      setErrors(validationErrors)
      return
    }
    setLoading(true)
    setSubmitError('')
    const { data, error } = await register(form)
    setLoading(false)
    if (error) {
      setSubmitError(error.message || 'Registration failed.')
      return
    }
    if (data) {
      setSubmitError('')
      navigate('/login', { state: { message: data } })
    }
  }

  return (
    <div className="auth-card">
      <h1>Register</h1>
      <p className="auth-subtitle">Create an account to manage your godowns.</p>
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
          User name (6–12 chars)
          <input
            type="text"
            name="userName"
            value={form.userName}
            onChange={handleChange}
            placeholder="User name"
            autoComplete="username"
            maxLength={USERNAME_MAX}
          />
        </label>
        <label>
          Password (6–12 chars)
          <input
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
            placeholder="Password"
            autoComplete="new-password"
            maxLength={PASSWORD_MAX}
          />
        </label>
        <label>
          Contact number (10 digits)
          <input
            type="text"
            name="contactNo"
            value={form.contactNo}
            onChange={handleChange}
            placeholder="10-digit contact number"
            inputMode="numeric"
            maxLength={CONTACT_LEN}
          />
        </label>
        <label>
          Address
          <textarea
            name="address"
            value={form.address}
            onChange={handleChange}
            placeholder="Address"
            rows={2}
          />
        </label>
        <button type="submit" className="btn-primary" disabled={loading}>
          {loading ? 'Registering…' : 'Register'}
        </button>
      </form>
      <p className="auth-footer">
        Already have an account? <Link to="/login">Login</Link>
      </p>
    </div>
  )
}
