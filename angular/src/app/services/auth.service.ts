import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http"
import "rxjs/add/operator/map";
import * as jwt_decode from "jwt-decode"

@Injectable()
export class AuthService {
  static tokenKey = 'id_token'
  private baseUrl = 'http://localhost:8080/kwetter/api'

  constructor(private http: HttpClient) { }

  private static getHeaders() {
    let headers = new HttpHeaders()
    headers.append('Content-Type', 'application/x-www-form-urlencoded')
    return headers
  }

  authenticate(username: string, password: string) {
    const body = new HttpParams().set("username", username).set("password", password)
    return this.http.post(this.baseUrl+'/user/login', body, {headers: AuthService.getHeaders(), observe: "response"})
      .map((data: any) => {
        this.setSession(data.body.token)
        return data
      })
  }

  private setSession(token: string) {
    if (!token) return
    window.localStorage.setItem(AuthService.tokenKey, token)
  }

  getSession() {
    return localStorage.getItem(AuthService.tokenKey)
  }

  logout() {
    localStorage.removeItem(AuthService.tokenKey)
  }

  isTokenExpired() : boolean {
    const token = this.getSession()
    if (!token) return true

    const date = AuthService.getTokenExpirationDate(token);
    if(!date) return false;
    return !(date.valueOf() > new Date().valueOf());
  }

  private static getTokenExpirationDate(token: string): Date {
    const decoded = jwt_decode(token);

    if (decoded.exp === undefined) return null;

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }
}
