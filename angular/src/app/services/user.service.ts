import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  private static getHeaders() {
    let headers = new HttpHeaders()
    headers.append('Content-Type', 'application/x-www-form-urlencoded')
    return headers
  }
}
