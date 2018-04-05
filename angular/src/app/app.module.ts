import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './modules/navbar/navbar.component';
import { HomeComponent } from './modules/home/home.component';
import { UserCardComponent } from './modules/user-card/user-card.component';
import { TrendingCardComponent } from './modules/trending-card/trending-card.component';

const routes: Routes = [
  {path:'', component: HomeComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    UserCardComponent,
    TrendingCardComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
