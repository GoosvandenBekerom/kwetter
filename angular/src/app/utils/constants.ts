import {HttpHeaders} from "@angular/common/http";

export const BASE_URL = 'http://localhost:8080/kwetter/api'

export function getHeaders() {
  let headers = new HttpHeaders()
  headers.append('Content-Type', 'application/x-www-form-urlencoded')
  return headers
}
