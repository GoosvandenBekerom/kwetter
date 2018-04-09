import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable()
export class AuthService {
  private baseUrl = 'http://localhost:8080/kwetter/api'

  constructor(private http: HttpClient) { }

  authenticate(credentials: any) {
    const body = new HttpParams()
      .set("username", credentials.username)
      .set("password", credentials.password)
    return this.http.post(this.baseUrl+'/user/login', body, {headers: AuthService.getHeaders(), observe: "response"})
  }

  private static getHeaders() {
    let headers = new HttpHeaders()
    headers.append('Content-Type', 'application/x-www-form-urlencoded')
    return headers
  }
}
