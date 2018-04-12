import {HttpHeaders} from "@angular/common/http";

export const BASE_URL = 'http://localhost:8080/kwetter/api'

export function basePostHeaders() : HttpHeaders {
  return new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded'
  })
}
