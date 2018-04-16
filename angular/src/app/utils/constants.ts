import {HttpHeaders} from "@angular/common/http";

const PROTOCOL_WS = 'ws://'
const PROTOCOL = 'http://'
const HOST = 'localhost:8080'
export const BASE_URL_WEBSOCKET = `${PROTOCOL_WS+HOST}/kwetter/action`
export const BASE_URL_API = `${PROTOCOL+HOST}/kwetter/api`

export function basePostHeaders() : HttpHeaders {
  return new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded'
  })
}
