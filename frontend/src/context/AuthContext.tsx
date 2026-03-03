import { createContext, useContext, useState, useCallback, type ReactNode } from 'react'
import type { UserLoginResponse } from '../api/godownManagement'

type AuthState = UserLoginResponse | null

type AuthContextValue = {
  user: AuthState
  setUser: (user: AuthState) => void
  login: (data: UserLoginResponse) => void
  logout: () => void
  isAuthenticated: boolean
}

const AuthContext = createContext<AuthContextValue | null>(null)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUserState] = useState<AuthState>(null)

  const setUser = useCallback((u: AuthState) => {
    setUserState(u)
  }, [])

  const login = useCallback((data: UserLoginResponse) => {
    setUserState(data)
  }, [])

  const logout = useCallback(() => {
    setUserState(null)
  }, [])

  const value: AuthContextValue = {
    user,
    setUser,
    login,
    logout,
    isAuthenticated: !!user,
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  const ctx = useContext(AuthContext)
  if (!ctx) throw new Error('useAuth must be used within AuthProvider')
  return ctx
}
