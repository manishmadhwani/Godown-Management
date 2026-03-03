import { api } from './client'

// DTOs aligned with backend
export interface UserRegisterRequest {
  userName: string
  password: string
  contactNo: string
  address: string
}

export interface UserLoginRequest {
  contactNo: string
  password: string
}

export interface UserLoginResponse {
  userName: string
  contactNo: string
}

export interface UserRequest {
  userName: string
  contactNo: string
}

export interface CityRequest {
  city: string
  state: string
}

export interface AddGodownRequest {
  userRequest: UserRequest
  name: string
  address: string
  cityRequest: CityRequest
}

export interface City {
  cityId: number
  name: string
  state: string
}

export interface Godown {
  godownId: number
  name: string
  address: string
  valuation: number
  owner?: { userName: string; contactNo: string }
  city?: City
  entries?: unknown[]
}

// GetAllGodownPerUser_5 – POST /getAllGodowns response
export interface ItemResponse {
  comodity: string
  markaName: string
  packing: number | string
  addressFrom: string
}

export interface EntryResponse {
  noOfPackings: number
  entryValuation: number
  entryDate: string
  itemResponse: ItemResponse
}

export interface GodownResponse {
  godownId?: number
  godownName: string
  godownAddress: string
  valuation: number
  entryResponses?: EntryResponse[]
}

export interface GetAllGodownsRequest {
  userName: string
  contactNo: string
}

// AddItems_ByUser – POST /addItems (backend expects List<AddItemRequest>, packing is int)
// When godownId is set, the backend also creates an Entry so the item appears under that godown.
export interface AddItemsRequest {
  userRequest: UserRequest
  comodity: string
  marka: string
  packing: number
  addressFrom: string
  godownId?: number
}

export interface AddItemsResponseItem {
  comodity: string
  markaName: string
  packing: string
  addressFrom: string
}

export async function register(data: UserRegisterRequest) {
  return api.post<string>('/register', data)
}

export async function login(data: UserLoginRequest) {
  return api.post<UserLoginResponse>('/login', data)
}

export async function fetchAllCities() {
  return api.get<City[]>('/fetchAllCities')
}

export async function addGodown(data: AddGodownRequest) {
  return api.post<string>('/addGodown', data)
}

// GetAllGodownPerUser_5 – POST /getAllGodowns
export async function getAllGodowns(data: GetAllGodownsRequest) {
  return api.post<GodownResponse[]>('/getAllGodowns', data)
}

export async function deleteGodown(godownId: number, contactNo: string) {
  return api.delete<string>(`/godown/${godownId}?contactNo=${encodeURIComponent(contactNo)}`)
}

// AddItems_ByUser – POST /addItems (backend expects array of items)
export async function addItems(items: AddItemsRequest[]) {
  return api.post<AddItemsResponseItem[]>('/addItems', items)
}
