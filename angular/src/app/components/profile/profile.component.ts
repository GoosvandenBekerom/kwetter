import { Component, OnInit } from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {HttpErrorResponse} from "@angular/common/http";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import "rxjs/add/operator/map";
import "rxjs/add/operator/filter";
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User
  loggedInUser: User

  kweets: Kweet[]
  editing: boolean

  constructor(
    private router: Router,
    private auth: AuthService,
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.loggedInUser = this.route.snapshot.data.user
    this.user = this.loggedInUser
    this.editing = false
    this.userService.getKweets(this.user.username).subscribe(
        (data: Kweet[]) => this.kweets = data,
        (err: HttpErrorResponse) => console.log(err.error.message)
      )
  }

  onEditClick() {
    this.editing = true
  }

  onEditDone(why: string) {
      this.editing = false
  }
}
