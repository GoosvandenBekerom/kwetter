import {Component, OnInit} from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {HttpErrorResponse} from "@angular/common/http";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {FlashService} from "../../services/flash.service";

import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/switchMap";
import "rxjs/add/operator/filter";
import 'rxjs/add/observable/of';
import "rxjs/add/operator/mergeMap";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User
  loggedInUser: User

  kweets: Kweet[]
  editing = false

  following: boolean
  followBtnText: string

  constructor(
    private router: Router,
    private auth: AuthService,
    private userService: UserService,
    private route: ActivatedRoute,
    private flash: FlashService
  ) {}

  ngOnInit() {
    this.loggedInUser = this.route.snapshot.data.user

    const profileUsername = this.route.params.map(params => params.username || this.loggedInUser.username)

    // get all kweets for this profile
    profileUsername
      .switchMap(username => username != this.loggedInUser.username
        ? this.userService.getUser(username)
        : Observable.of(this.loggedInUser))
      .switchMap((user: User) => {
        this.user = user
        return this.userService.getKweets(user.username)
      })
      .subscribe(
        (data: Kweet[]) => this.kweets = data,
        (err: HttpErrorResponse) => {
          if (err.status == 404)  this.flash.warn(err.error.message)
          else this.flash.error()
          this.router.navigate(['error'], { skipLocationChange: true })
        }
      )

    // Check if the logged in user follows this user
    profileUsername
      .switchMap(username => this.userService.isUserFollowedByUser(this.loggedInUser, username))
      .subscribe(followed => this.following = followed)
  }

  onEditClick() {
    this.editing = true
  }

  onEditDone() {
      this.editing = false
  }

  onFollowClick() {
    if (this.following) {
      this.userService.unfollowUser(this.user.username)
        .subscribe((res: any) => {
          this.flash.success(res.message)
          this.following = false
          this.followBtnText = 'Follow'
        }, console.error)
    } else {
      this.userService.followUser(this.user.username)
        .subscribe((res: any) => {
          this.flash.success(res.message)
          this.following = true
          this.followBtnText = 'Unfollow'
        }, console.error)
    }
  }
}
