import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {User} from "../models/User";
import {Observable} from "rxjs/Observable";
import {AuthService} from "../services/auth.service";
import {Injectable} from "@angular/core";

@Injectable()
export class UserResolver implements Resolve<User> {
  constructor(private auth: AuthService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User> | Promise<User> | User {
    return this.auth.getLoggedInUser().filter(user => user !== undefined)
  }
}
