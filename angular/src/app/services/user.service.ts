import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_URL_API, basePostHeaders} from "../utils/constants";
import {User} from "../models/User";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  getUser(username: string) {
    return this.http.get(`${BASE_URL_API}/user/${username}`)
  }

  register(username: string, password: string, fullName: string) {
    const body = new HttpParams().set("username", username).set("password", password).set("fullName", fullName)
    return this.http.post(`${BASE_URL_API}/user`, body, {headers: basePostHeaders(), observe: "response"})
  }

  getKweets(username: string, offset: number = 0, limit: number = 50) {
    return this.http.get(`${BASE_URL_API}/user/${username}/kweets?offset=${offset}&limit=${limit}`)
  }

  updateFullname(user: User) {
    const body = new HttpParams().set('fullName', user.fullName)
    return this.http.put(`${BASE_URL_API}/user/settings/fullName`, body, { headers: basePostHeaders() })
  }

  updatePassword(oldPass: string, newPass: string) {
    const body = new HttpParams().set('password', oldPass).set('newPassword', newPass)
    return this.http.put(`${BASE_URL_API}/user/settings/password`, body, { headers: basePostHeaders() })
  }

  getFollowers(user: User) {
    return this.http.get<User[]>(this.getUrlByRel(user, 'followers'))
  }

  getFollowing(user: User) {
    return this.http.get<User[]>(this.getUrlByRel(user, 'following'))
  }

  isUserFollowedByUser(user: User, username: string) : Observable<boolean> {
    return this.getFollowing(user)
      .map(followers => followers.find(follower => follower.username == username) !== undefined)
  }

  followUser(username: string) {
    return this.http.post(`${BASE_URL_API}/user/${username}/follow`, null)
  }

  unfollowUser(username: string) {
    const body = new HttpParams().set("username", username)
    return this.http.post(`${BASE_URL_API}/user/${username}/unfollow`, null)
  }

  private getUrlByRel(user: User, rel: string): string {
    return user.links.find(link => link.rel == rel).href
  }
}
