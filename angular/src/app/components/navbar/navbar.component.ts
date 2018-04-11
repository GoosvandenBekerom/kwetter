import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  query: string

  constructor(private router: Router) {}

  ngOnInit() {}

  search() {
    if (!this.query) return
    this.router.navigate(['/search', this.query])
  }

  isLoggedIn() {
    return !AuthService.isTokenExpired()
  }

  onLogoutClick() {
    AuthService.logout()
    this.router.navigate(['/login'])
  }
}
