import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_URL, getHeaders} from "../utils/constants";

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  register(username: string, password: string, fullName: string) {
    const body = new HttpParams().set("username", username).set("password", password).set("fullName", fullName)
    return this.http.post(`${BASE_URL}/user`, body, {headers: getHeaders(), observe: "response"})
  }
}
