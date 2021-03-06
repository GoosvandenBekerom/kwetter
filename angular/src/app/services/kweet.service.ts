import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_URL_API, basePostHeaders} from "../utils/constants";
import {Kweet} from "../models/Kweet";
import {Observable} from "rxjs/Observable";

@Injectable()
export class KweetService {
  constructor(private http: HttpClient) { }

  postKweet(message: string) : Observable<Kweet>{
    const body = new HttpParams().set('message', message)
    return this.http.post<Kweet>(`${BASE_URL_API}/kweet`, body, { headers: basePostHeaders() })
  }

  toggleLike(kweet: Kweet) {
    return this.http.post(`${BASE_URL_API}/kweet/${kweet.id}/like`, null)
  }

  deleteKweet(kweet: Kweet) {
    return this.http.delete(`${BASE_URL_API}/kweet/${kweet.id}`, { headers: basePostHeaders() })
  }
}
