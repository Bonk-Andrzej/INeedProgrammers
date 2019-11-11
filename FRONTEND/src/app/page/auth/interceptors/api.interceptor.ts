import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

import {Observable} from 'rxjs';
import {JwtService} from "../service/jwt.service";

@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  /**
   * Create a new instance.
   *
   * @param {JwtService} jwtService
   */
  constructor(private jwtService: JwtService) {
    //
  }

  /**
   * Intercept the http request.
   *
   * @param {HttpRequest<any>} request
   * @param {HttpHandler} next
   * @return {Observable<HttpEvent<any>>}
   */
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let token;
    if ((token = this.jwtService.get())) {
      request = request.clone({
        headers: request.headers.append('Authorization', `Bearer ${token}`)
      });
    }

    return next.handle(request);
  }
}
