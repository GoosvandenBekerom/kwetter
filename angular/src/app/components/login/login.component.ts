import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string
  password: string

  constructor(private authService: AuthService) { }

  ngOnInit() {}

  onLoginSubmit() {
    this.authService.authenticate(this.username, this.password)
      .subscribe(() => {
        console.log("authentication successful")
      },
      (err: HttpErrorResponse) => {
        console.log(err.error.message);
      }
    )
  }
}
