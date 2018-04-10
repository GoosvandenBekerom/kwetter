import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { AuthService } from "../services/auth.service";
import { Observable } from "rxjs/Observable";

const AUTH_HEADER_KEY = 'Authorization'
const AUTH_PREFIX = 'Bearer'

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authHeader = `${AUTH_PREFIX} ${this.auth.getSession()}`;
    const authReq = req.clone({headers: req.headers.set(AUTH_HEADER_KEY, authHeader)});
    return next.handle(authReq);
  }
}
