import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Hashtag} from "../models/Hashtag";
import {BASE_URL} from "../utils/constants";

@Injectable()
export class HashtagService {
  constructor(private http: HttpClient) { }

  getTopHashtags() {
    return this.http.get<Hashtag[]>(`${BASE_URL}/hashtag/top`)
  }
}
