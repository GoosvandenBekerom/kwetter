import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_URL, basePostHeaders} from "../utils/constants";
import {User} from "../models/User";

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  register(username: string, password: string, fullName: string) {
    const body = new HttpParams().set("username", username).set("password", password).set("fullName", fullName)
    return this.http.post(`${BASE_URL}/user`, body, {headers: basePostHeaders(), observe: "response"})
  }

  getKweets(username: string, offset: number = 0, limit: number = 50) {
    return this.http.get(`${BASE_URL}/user/${username}/kweets?offset=${offset}&limit=${limit}`)
  }

  updateFullname(user: User) {
    const body = new HttpParams().set('fullName', user.fullName)
    return this.http.put(`${BASE_URL}/user/settings/fullName`, body, { headers: basePostHeaders() })
  }
}
