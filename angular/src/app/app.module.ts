import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { UserCardComponent } from './components/user-card/user-card.component';
import { TrendingCardComponent } from './components/trending-card/trending-card.component';
import { KweetFormComponent } from './components/kweet-form/kweet-form.component';
import { KweetComponent } from './components/kweet/kweet.component';
import { SearchComponent } from './components/search/search.component';
import { LoginComponent } from './components/login/login.component';

import { AuthService } from "./services/auth.service";
import { AuthGuard } from "./guards/auth.guard";
import {AuthInterceptor} from "./interceptors/AuthInterceptor";


const routes: Routes = [
  {path:'', component: HomeComponent, canActivate: [AuthGuard] },
  {path:'login', component: LoginComponent },
  {path:'search/:query', component: SearchComponent, canActivate: [AuthGuard] }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    UserCardComponent,
    TrendingCardComponent,
    KweetFormComponent,
    KweetComponent,
    SearchComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
