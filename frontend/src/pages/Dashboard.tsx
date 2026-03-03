import { useState, useEffect, useCallback } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import {
  fetchAllCities,
  addGodown,
  getAllGodowns,
  addItems,
  type AddGodownRequest,
  type City,
  type GodownResponse,
} from '../api/godownManagement'
import './Dashboard.css'

function validateAddGodown(form: { name: string; address: string; cityState: string }): string[] {
  const errors: string[] = []
  if (!form.name?.trim()) errors.push('Godown name is required.')
  if (!form.address?.trim()) errors.push('Godown address is required.')
  if (!form.cityState?.trim()) errors.push('City and state are required.')
  return errors
}

export default function Dashboard() {
  const { user } = useAuth()
  const [cities, setCities] = useState<City[]>([])
  const [godowns, setGodowns] = useState<GodownResponse[]>([])
  const [loadingCities, setLoadingCities] = useState(true)
  const [loadingGodowns, setLoadingGodowns] = useState(true)
  const [citiesError, setCitiesError] = useState('')
  const [godownError, setGodownError] = useState('')
  const [addForm, setAddForm] = useState({ name: '', address: '', cityState: '' })
  const [addErrors, setAddErrors] = useState<string[]>([])
  const [addSubmitError, setAddSubmitError] = useState('')
  const [addLoading, setAddLoading] = useState(false)
  const [successMsg, setSuccessMsg] = useState('')
  const [expandedGodownIndex, setExpandedGodownIndex] = useState<number | null>(null)
  const [itemForm, setItemForm] = useState({ comodity: '', marka: '', packing: '', addressFrom: '' })
  const [itemFormError, setItemFormError] = useState('')
  const [itemFormLoading, setItemFormLoading] = useState(false)

  const loadCities = useCallback(async () => {
    setLoadingCities(true)
    setCitiesError('')
    const { data, error } = await fetchAllCities()
    setLoadingCities(false)
    if (error) {
      setCitiesError(error.message || 'Failed to load cities.')
      setCities([])
      return
    }
    setCities(Array.isArray(data) ? data : [])
  }, [])

  const loadGodowns = useCallback(async () => {
    if (!user?.userName || !user?.contactNo) {
      setLoadingGodowns(false)
      return
    }
    setLoadingGodowns(true)
    setGodownError('')
    const { data, error } = await getAllGodowns({
      userName: user.userName,
      contactNo: user.contactNo,
    })
    setLoadingGodowns(false)
    if (error) {
      // Backend returns 404 when user has no godowns (empty list) – treat as success with []
      if (error.status === 404) {
        setGodowns([])
        setGodownError('')
        return
      }
      setGodownError(error.message || 'Failed to load godowns.')
      setGodowns([])
      return
    }
    const list = Array.isArray(data) ? data : []
    setGodowns([...list].sort((a, b) => (a.godownName ?? '').localeCompare(b.godownName ?? '')))
  }, [user?.userName, user?.contactNo])

  useEffect(() => {
    loadCities()
  }, [loadCities])

  useEffect(() => {
    loadGodowns()
  }, [loadGodowns])

  const handleAddChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target
    setAddForm((prev) => ({ ...prev, [name]: value }))
    setAddErrors([])
    setAddSubmitError('')
  }

  const handleItemFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setItemForm((prev) => ({ ...prev, [name]: value }))
    setItemFormError('')
  }

  const handleAddItemSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!user) return
    const packingNum = Number(itemForm.packing.trim())
    if (!itemForm.comodity?.trim() || !itemForm.marka?.trim() || !itemForm.packing?.trim() || !itemForm.addressFrom?.trim()) {
      setItemFormError('All fields are required.')
      return
    }
    if (isNaN(packingNum)) {
      setItemFormError('Packing must be a number.')
      return
    }
    setItemFormLoading(true)
    setItemFormError('')
    const expandedGodown = expandedGodownIndex != null ? godowns[expandedGodownIndex] : null
    const godownId = expandedGodown?.godownId
    const { error } = await addItems([{
      userRequest: { userName: user.userName, contactNo: user.contactNo },
      comodity: itemForm.comodity.trim(),
      marka: itemForm.marka.trim(),
      packing: isNaN(packingNum) ? 0 : packingNum,
      addressFrom: itemForm.addressFrom.trim(),
      ...(godownId != null && { godownId }),
    }])
    setItemFormLoading(false)
    if (error) {
      setItemFormError(error.message || 'Failed to add item.')
      return
    }
    setItemForm({ comodity: '', marka: '', packing: '', addressFrom: '' })
    setExpandedGodownIndex(null)
    loadGodowns()
  }

  const handleAddSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    const validationErrors = validateAddGodown(addForm)
    if (validationErrors.length) {
      setAddErrors(validationErrors)
      return
    }
    if (!user) return
    const [city, state] = addForm.cityState.split('|')
    const payload: AddGodownRequest = {
      userRequest: { userName: user.userName, contactNo: user.contactNo },
      name: addForm.name.trim(),
      address: addForm.address.trim(),
      cityRequest: { city: city?.trim() ?? '', state: state?.trim() ?? '' },
    }
    setAddLoading(true)
    setAddSubmitError('')
    const { data, error } = await addGodown(payload)
    setAddLoading(false)
    if (error) {
      setAddSubmitError(error.message || 'Failed to add godown.')
      return
    }
    if (data) {
      setSuccessMsg(data)
      setAddForm({ name: '', address: '', cityState: '' })
      loadGodowns()
      loadCities()
    }
  }

  const citiesList = Array.isArray(cities) ? cities : []
  const cityStateOptions = citiesList.map((c) => ({
    value: `${c.name}|${c.state}`,
    label: `${c.name}, ${c.state}`,
  }))
  const uniqueCityState = Array.from(
    new Map(cityStateOptions.map((o) => [o.value, o])).values()
  )

  const isLoggedIn = !!user

  return (
    <div className="dashboard">
      <h1>Dashboard</h1>
      <p className="dashboard-subtitle">
        {isLoggedIn ? `Welcome, ${user.userName}. Manage your godowns below.` : 'View the dashboard. Log in to add and manage your godowns.'}
      </p>

      {!isLoggedIn && (
        <p className="dashboard-login-prompt">
          <Link to="/login">Log in</Link> to add godowns and see your list.
        </p>
      )}

      {successMsg && (
        <p className="success-msg" onAnimationEnd={() => setSuccessMsg('')}>
          {successMsg}
        </p>
      )}

      {isLoggedIn && (
      <section className="section add-godown-section">
        <h2>Add Godown</h2>
        <form onSubmit={handleAddSubmit} className="dashboard-form">
          {addErrors.length > 0 && (
            <ul className="error-list">
              {addErrors.map((err, i) => (
                <li key={i}>{err}</li>
              ))}
            </ul>
          )}
          {addSubmitError && <p className="error-msg">{addSubmitError}</p>}
          <div className="form-row">
            <label>
              Godown name
              <input
                type="text"
                name="name"
                value={addForm.name}
                onChange={handleAddChange}
                placeholder="Name"
                required
              />
            </label>
            <label>
              Address
              <input
                type="text"
                name="address"
                value={addForm.address}
                onChange={handleAddChange}
                placeholder="Address"
                required
              />
            </label>
          </div>
          <label>
            City & State (from backend)
            {citiesError && (
              <span className="inline-error-block">
                <span className="error-msg inline-error">{citiesError}</span>
                <button type="button" className="btn-retry" onClick={() => loadCities()}>
                  Retry
                </button>
              </span>
            )}
            <select
              name="cityState"
              value={addForm.cityState}
              onChange={handleAddChange}
              required
              disabled={loadingCities}
            >
              <option value="">
                {loadingCities ? 'Loading cities…' : uniqueCityState.length === 0 && !citiesError ? 'No cities available' : uniqueCityState.length === 0 ? 'Could not load cities (use Retry)' : 'Select city and state'}
              </option>
              {uniqueCityState.map((o) => (
                <option key={o.value} value={o.value}>
                  {o.label}
                </option>
              ))}
            </select>
          </label>
          <button type="submit" className="btn-primary" disabled={addLoading || loadingCities}>
            {addLoading ? 'Adding…' : 'Add Godown'}
          </button>
        </form>
      </section>
      )}

      <section className="section godowns-section">
        <h2>My Godowns</h2>
        {godownError && (
          <div className="inline-error-block">
            <span className="error-msg inline-error">{godownError}</span>
            <button type="button" className="btn-retry" onClick={() => loadGodowns()}>
              Retry
            </button>
          </div>
        )}
        {!isLoggedIn ? (
          <p className="muted">Log in to see your godowns.</p>
        ) : loadingGodowns ? (
          <p className="muted">Loading godowns…</p>
        ) : godowns.length === 0 ? (
          <p className="muted">No godowns yet. Add one above.</p>
        ) : (
          <ul className="godown-list">
            {godowns.map((g, idx) => (
              <li key={g.godownId ?? `godown-${idx}`} className="godown-card">
                <div
                  className="godown-info godown-info-clickable"
                  onClick={() => setExpandedGodownIndex(expandedGodownIndex === idx ? null : idx)}
                  role="button"
                  tabIndex={0}
                  onKeyDown={(e) => e.key === 'Enter' && setExpandedGodownIndex(expandedGodownIndex === idx ? null : idx)}
                  aria-label={expandedGodownIndex === idx ? 'Collapse add items form' : `Add items to ${g.godownName}`}
                >
                  <strong>{g.godownName}</strong>
                  <span className="godown-address">{g.godownAddress}</span>
                  <span className="godown-valuation" title="Set by backend; can be updated when entries are added">
                    Valuation: {g.valuation ?? 0}
                  </span>
                  <div className="godown-entries">
                    <span className="entries-label">Items in this godown ({g.entryResponses?.length ?? 0})</span>
                    {g.entryResponses && g.entryResponses.length > 0 ? (
                      <ul className="entry-list">
                        {g.entryResponses.map((e, i) => (
                          <li key={i} className="entry-item-card">
                            <div className="entry-item-row">
                              <span className="entry-item-field"><strong>Comodity:</strong> {e.itemResponse?.comodity ?? '–'}</span>
                              <span className="entry-item-field"><strong>Marka:</strong> {e.itemResponse?.markaName ?? '–'}</span>
                            </div>
                            <div className="entry-item-row">
                              <span className="entry-item-field"><strong>Packing:</strong> {e.itemResponse?.packing ?? '–'}</span>
                              <span className="entry-item-field"><strong>Address From:</strong> {e.itemResponse?.addressFrom ?? '–'}</span>
                            </div>
                            <div className="entry-item-row">
                              <span className="entry-item-field"><strong>No. of packings:</strong> {e.noOfPackings ?? 0}</span>
                              <span className="entry-item-field"><strong>Entry valuation:</strong> {e.entryValuation ?? 0}</span>
                              {e.entryDate && (
                                <span className="entry-item-field"><strong>Date:</strong> {new Date(e.entryDate).toLocaleDateString()}</span>
                              )}
                            </div>
                          </li>
                        ))}
                      </ul>
                    ) : (
                      <p className="godown-no-items">No items in this godown yet. Click below to add items.</p>
                    )}
                  </div>
                  <span className="godown-click-hint">{expandedGodownIndex === idx ? '▼ Hide form' : '▶ Add items'}</span>
                </div>
                {expandedGodownIndex === idx && (
                  <form onSubmit={handleAddItemSubmit} className="godown-add-item-form">
                    <h3 className="godown-add-item-title">Add item to {g.godownName}</h3>
                    {itemFormError && <p className="error-msg">{itemFormError}</p>}
                    <div className="godown-add-item-fields">
                      <label>
                        Comodity
                        <input type="text" name="comodity" value={itemForm.comodity} onChange={handleItemFormChange} placeholder="Comodity" required />
                      </label>
                      <label>
                        Marka
                        <input type="text" name="marka" value={itemForm.marka} onChange={handleItemFormChange} placeholder="Marka" required />
                      </label>
                      <label>
                        Packing
                        <input type="text" name="packing" value={itemForm.packing} onChange={handleItemFormChange} placeholder="Packing (number)" required />
                      </label>
                      <label>
                        Address From
                        <input type="text" name="addressFrom" value={itemForm.addressFrom} onChange={handleItemFormChange} placeholder="Address From" required />
                      </label>
                    </div>
                    <div className="godown-add-item-actions">
                      <button type="submit" className="btn-primary" disabled={itemFormLoading}>
                        {itemFormLoading ? 'Adding…' : 'Add Item'}
                      </button>
                      <button type="button" className="btn-secondary" onClick={() => setExpandedGodownIndex(null)}>
                        Cancel
                      </button>
                    </div>
                  </form>
                )}
              </li>
            ))}
          </ul>
        )}
      </section>
    </div>
  )
}
