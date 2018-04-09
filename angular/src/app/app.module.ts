import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { UserCardComponent } from './components/user-card/user-card.component';
import { TrendingCardComponent } from './components/trending-card/trending-card.component';
import { KweetFormComponent } from './components/kweet-form/kweet-form.component';
import { KweetComponent } from './components/kweet/kweet.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  {path:'', component: HomeComponent },
  {path:'search/:query', component: SearchComponent }
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
    SearchComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
