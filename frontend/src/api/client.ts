const BASE_URL = '/api/godownManagement'

export type ApiError = { message: string; status?: number }

async function request<T>(
  path: string,
  options: RequestInit = {}
): Promise<{ data?: T; error?: ApiError }> {
  const url = `${BASE_URL}${path}`
  const headers: HeadersInit = {
    'Content-Type': 'application/json',
    ...options.headers,
  }
  try {
    const res = await fetch(url, { ...options, headers })
    const text = await res.text()
    let body: unknown
    try {
      body = text ? JSON.parse(text) : null
    } catch {
      body = text
    }
    if (!res.ok) {
      let message: string
      if (typeof body === 'object' && body !== null) {
        const o = body as Record<string, unknown>
        if (typeof o.message === 'string') message = o.message
        else if (Object.keys(o).length > 0)
          message = Object.values(o).filter((v): v is string => typeof v === 'string').join('. ')
        else message = text || `Request failed: ${res.status}`
      } else if (typeof text === 'string' && text.length > 0) {
        message = text
      } else {
        message = `Request failed: ${res.status}`
      }
      return { error: { message, status: res.status } }
    }
    return { data: body as T }
  } catch (e) {
    const raw = e instanceof Error ? e.message : 'Network error'
    const message =
      raw === 'Failed to fetch' || raw.toLowerCase().includes('network')
        ? 'Cannot connect to backend. Make sure the backend is running on http://localhost:8087'
        : raw
    return { error: { message } }
  }
}

export const api = {
  get: <T>(path: string) => request<T>(path, { method: 'GET' }),
  post: <T>(path: string, body: unknown) =>
    request<T>(path, { method: 'POST', body: JSON.stringify(body) }),
  delete: <T>(path: string) => request<T>(path, { method: 'DELETE' }),
}
