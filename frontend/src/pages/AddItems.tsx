import { useState } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { addItems, type AddItemsResponseItem } from '../api/godownManagement'
import './AddItems.css'

function validateForm(form: { comodity: string; marka: string; packing: string; addressFrom: string }): string[] {
  const errors: string[] = []
  if (!form.comodity?.trim()) errors.push('Comodity is required.')
  if (!form.marka?.trim()) errors.push('Marka is required.')
  if (!form.packing?.trim()) errors.push('Packing is required.')
  else if (isNaN(Number(form.packing))) errors.push('Packing must be a number.')
  if (!form.addressFrom?.trim()) errors.push('Address From is required.')
  return errors
}

export default function AddItems() {
  const { user } = useAuth()
  const [form, setForm] = useState({ comodity: '', marka: '', packing: '', addressFrom: '' })
  const [errors, setErrors] = useState<string[]>([])
  const [submitError, setSubmitError] = useState('')
  const [loading, setLoading] = useState(false)
  const [responseItems, setResponseItems] = useState<AddItemsResponseItem[] | null>(null)

  const isLoggedIn = !!user

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setForm((prev) => ({ ...prev, [name]: value }))
    setErrors([])
    setSubmitError('')
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    const validationErrors = validateForm(form)
    if (validationErrors.length) {
      setErrors(validationErrors)
      return
    }
    if (!user) return
    setLoading(true)
    setSubmitError('')
    setResponseItems(null)
    const packingNum = Number(form.packing.trim())
    const { data, error } = await addItems([
      {
        userRequest: { userName: user.userName, contactNo: user.contactNo },
        comodity: form.comodity.trim(),
        marka: form.marka.trim(),
        packing: isNaN(packingNum) ? 0 : packingNum,
        addressFrom: form.addressFrom.trim(),
      },
    ])
    setLoading(false)
    if (error) {
      setSubmitError(error.message || 'Failed to add items.')
      return
    }
    if (data && data.length >= 0) {
      setResponseItems(data)
      setForm({ comodity: '', marka: '', packing: '', addressFrom: '' })
    }
  }

  if (!isLoggedIn) {
    return (
      <div className="add-items-page">
        <h1>Add Items</h1>
        <p className="muted">Please <Link to="/login">log in</Link> to add items.</p>
      </div>
    )
  }

  return (
    <div className="add-items-page">
      <h1>Add Items</h1>
      <p className="add-items-subtitle">Add items to the system. Items contain comodity, marka, packing, and addressFrom.</p>

      <section className="section add-items-section">
        <h2>New Item</h2>
        <form onSubmit={handleSubmit} className="add-items-form">
          {errors.length > 0 && (
            <ul className="error-list">
              {errors.map((err, i) => (
                <li key={i}>{err}</li>
              ))}
            </ul>
          )}
          {submitError && <p className="error-msg">{submitError}</p>}
          <label>
            Comodity
            <input
              type="text"
              name="comodity"
              value={form.comodity}
              onChange={handleChange}
              placeholder="Comodity"
              required
            />
          </label>
          <label>
            Marka
            <input
              type="text"
              name="marka"
              value={form.marka}
              onChange={handleChange}
              placeholder="Marka"
              required
            />
          </label>
          <label>
            Packing
            <input
              type="text"
              name="packing"
              value={form.packing}
              onChange={handleChange}
              placeholder="Packing"
              required
            />
          </label>
          <label>
            Address From
            <input
              type="text"
              name="addressFrom"
              value={form.addressFrom}
              onChange={handleChange}
              placeholder="Address From"
              required
            />
          </label>
          <button type="submit" className="btn-primary" disabled={loading}>
            {loading ? 'Adding…' : 'Add Item'}
          </button>
        </form>
      </section>

      {responseItems !== null && responseItems.length > 0 && (
        <section className="section response-section">
          <h2>Added items</h2>
          <ul className="response-item-list">
            {responseItems.map((item, i) => (
              <li key={i} className="response-item">
                <span><strong>Comodity:</strong> {item.comodity}</span>
                <span><strong>Marka:</strong> {item.markaName}</span>
                <span><strong>Packing:</strong> {item.packing}</span>
                <span><strong>Address From:</strong> {item.addressFrom}</span>
              </li>
            ))}
          </ul>
        </section>
      )}
    </div>
  )
}
