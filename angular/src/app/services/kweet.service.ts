import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_URL, basePostHeaders} from "../utils/constants";
import {Kweet} from "../models/Kweet";
import {Observable} from "rxjs/Observable";

@Injectable()
export class KweetService {
  constructor(private http: HttpClient) { }

  postKweet(message: string) : Observable<Kweet>{
    const body = new HttpParams().set('message', message)
    return this.http.post<Kweet>(`${BASE_URL}/kweet`, body, { headers: basePostHeaders() })
  }

  likeKweet(kweet: Kweet) {
    return this.http.post(`${BASE_URL}/kweet/${kweet.id}/like`, null)
  }

  deleteKweet(kweet: Kweet) {
    return this.http.delete(`${BASE_URL}/kweet/${kweet.id}`, { headers: basePostHeaders() })
  }
}
