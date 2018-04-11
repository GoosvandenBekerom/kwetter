import { Component, OnInit } from '@angular/core';
import {FlashService} from "../../services/flash.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username: string
  password: string
  password_again: string
  fullName: string

  constructor(
    private userService: UserService,
    private router: Router,
    private flash: FlashService) { }

  ngOnInit() {}

  onRegisterSubmit() {
    if (this.username == undefined || this.password == undefined || this.fullName == undefined) {
      this.flash.error('Make sure you fill out all fields')
      return
    }
    if (this.password !== this.password_again) {
      this.flash.error('The passwords you entered do not match')
      return
    }
    this.userService.register(this.username, this.password, this.fullName)
      .subscribe(
        () => {
          this.flash.success('Successfully registered!')
          this.router.navigate(['/login'])
        },
        (err: HttpErrorResponse) => this.flash.error(err.error.message)
      )
  }
}
