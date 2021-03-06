import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Hashtag} from "../models/Hashtag";
import {BASE_URL_API} from "../utils/constants";
import {Kweet} from "../models/Kweet";

@Injectable()
export class HashtagService {
  constructor(private http: HttpClient) { }

  getTopHashtags() {
    return this.http.get<Hashtag[]>(`${BASE_URL_API}/hashtag/top`)
  }

  getTimelineForTag(tag: string) {
    return this.http.get<Kweet[]>(`${BASE_URL_API}/timeline/hashtag/${tag}`)
  }
}
