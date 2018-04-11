import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BASE_URL} from "../utils/constants";
import {Kweet} from "../models/Kweet";

@Injectable()
export class TimelineService {

  constructor(private http: HttpClient) { }

  forUser(username: string) {
    return this.http.get<Kweet[]>(`${BASE_URL}/timeline/user/${username}`)
  }
}
