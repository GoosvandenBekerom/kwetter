import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../services/auth.service";
import { HttpErrorResponse } from "@angular/common/http";
import { FlashService } from "../../services/flash.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string
  password: string

  constructor(
    private authService: AuthService,
    private router: Router,
    private flash: FlashService) { }

  ngOnInit() {}

  onLoginSubmit() {
    this.authService.authenticate(this.username, this.password)
      .subscribe(
        () => {
          this.flash.success('Successfully logged in!')
          this.router.navigate(['/'])
        },
        (err: HttpErrorResponse) => this.flash.error(err.error.message)
      )
  }
}
