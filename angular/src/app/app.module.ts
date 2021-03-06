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

import { UserService} from "./services/user.service";
import { AuthService } from "./services/auth.service";
import { AuthGuard } from "./guards/auth.guard";
import { AuthInterceptor } from "./interceptors/AuthInterceptor";

import { FlashMessagesModule } from 'angular2-flash-messages';
import { FlashService } from "./services/flash.service";
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { TimelineComponent } from './components/timeline/timeline.component';
import {TimelineService} from "./services/timeline.service";
import {UserResolver} from "./resolvers/UserResolver";
import { EditUserCardComponent } from './components/edit-user-card/edit-user-card.component';
import {KweetService} from "./services/kweet.service";
import {HashtagService} from "./services/hashtag.service";
import {HashtagTopResolver} from "./resolvers/HashtagTopResolver";
import { ErrorComponent } from './components/error/error.component';
import { KweetListComponent } from './components/kweet-list/kweet-list.component';
import {WebsocketService} from "./services/websocket.service";
import {KweetSubjectService} from "./services/kweet-subject.service";

const routes: Routes = [
  {
    path:'', component: HomeComponent, canActivate: [AuthGuard],
    resolve: { user: UserResolver, topHashtags: HashtagTopResolver }
  },
  { path:'login', component: LoginComponent },
  { path:'register', component: RegisterComponent },
  {
    path:'profile', component: ProfileComponent,
    resolve: { user: UserResolver }, canActivate: [AuthGuard]
  },
  {
    path:'profile/:username', component: ProfileComponent,
    resolve: { user: UserResolver }, canActivate: [AuthGuard]
  },
  {
    path:'hashtag/:hashtag', component: KweetListComponent,
    resolve: { user: UserResolver }, canActivate: [AuthGuard]
  },
  { path:'search/:query', component: SearchComponent, canActivate: [AuthGuard] },
  { path:'error', component: ErrorComponent },
  { path: '**', component: ErrorComponent }
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
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    TimelineComponent,
    EditUserCardComponent,
    ErrorComponent,
    KweetListComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    FlashMessagesModule.forRoot()
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthService,
    AuthGuard,
    UserResolver,
    HashtagTopResolver,
    FlashService,
    UserService,
    TimelineService,
    KweetService,
    HashtagService,
    WebsocketService,
    KweetSubjectService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
