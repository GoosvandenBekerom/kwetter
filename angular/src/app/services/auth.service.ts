import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http"
import "rxjs/add/operator/map";
import * as jwt_decode from 'jwt-decode'
import { BASE_URL, getHeaders } from "../utils/constants";

@Injectable()
export class AuthService {
  static tokenKey = 'id_token'

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string) {
    const body = new HttpParams().set("username", username).set("password", password)
    return this.http.post(`${BASE_URL}/user/login`, body, {headers: getHeaders(), observe: "response"})
      .map((data: any) => {
        AuthService.setSession(data.body.token)
        return data
      })
  }

  private static setSession(token: string) {
    if (!token) return
    window.localStorage.setItem(AuthService.tokenKey, token)
  }

  static getSession() {
    return localStorage.getItem(AuthService.tokenKey)
  }

  static logout() {
    localStorage.removeItem(AuthService.tokenKey)
  }

  static isTokenExpired() : boolean {
    const token = AuthService.getSession()
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
