import { Injectable } from '@angular/core'
import { HttpClient, HttpParams } from "@angular/common/http"
import "rxjs/add/operator/map";
import * as jwt_decode from 'jwt-decode'
import { BASE_URL_API, basePostHeaders } from "../utils/constants";
import {User} from "../models/User";
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {
  static tokenKey = 'id_token'

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string) {
    const body = new HttpParams().set("username", username).set("password", password)
    return this.http.post(`${BASE_URL_API}/user/login`, body, {headers: basePostHeaders(), observe: "response"})
      .map((data: any) => {
        AuthService.setSession(data.body.token)
        return data
      })
  }

  getLoggedInUser() : Observable<User> {
    const token = AuthService.getSession()
    if (!token) return null

    const decoded = jwt_decode(token)
    return this.http.get<User>(`${BASE_URL_API}/user/${decoded.user}`)
  }

  getLoggedInUsername() : string {
    const token = AuthService.getSession()
    if (!token) return null
    return jwt_decode(token).user
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
