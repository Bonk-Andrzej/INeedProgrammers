import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {TranslateService} from '@ngx-translate/core';
import {Router} from '@angular/router';

import {EMPTY, Observable, throwError as observableThrowError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {JwtService} from "../service/jwt.service";


enum HTTP {
  UNKNOWN = 0,
  UNAUTHORIZED = 401,
  FORBIDDEN = 403,
  NOT_FOUND = 404,
  HTTP_INTERNAL_SERVER_ERROR = 500
}

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  /**
   * Create a new instance.
   *
   * @param {Router} router
   * @param {MatSnackBar} snackBar
   * @param {JwtService} jwtService
   * @param {TranslateService} translateService
   */
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private jwtService: JwtService,
    private translateService: TranslateService
  ) {
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
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        const {status} = error;

        if (
          status == HTTP.UNKNOWN ||
          status == HTTP.HTTP_INTERNAL_SERVER_ERROR
        ) {
          this.translateService.get('MESSAGES.error').subscribe(message => {
            this.snackBar.open(message, null);
          });

          return EMPTY;
        }

        if (status === HTTP.NOT_FOUND) {
          this.router.navigate(['/']);

          return EMPTY;
        }

        if (this.jwtService.get() && status === HTTP.UNAUTHORIZED) {
          this.jwtService.clear();

          this.router.navigate(['/login']);

          return EMPTY;
        }

        return observableThrowError(error);
      })
    );
  }
}
