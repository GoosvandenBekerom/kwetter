import { Component, OnInit } from '@angular/core';
import {FlashService} from "../../services/flash.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username: string
  password: string
  fullName: string

  constructor(
    private userService: UserService,
    private router: Router,
    private flash: FlashService) { }

  ngOnInit() {}

  onRegisterSubmit() {

  }
}
