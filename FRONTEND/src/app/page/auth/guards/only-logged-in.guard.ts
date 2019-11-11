import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {JwtService} from "../service/jwt.service";


@Injectable({
  providedIn: 'root'
})
export class OnlyLoggedInGuard implements CanActivate {
  /**
   * @var {string}
   */
  private static readonly REDIRECT_TO: string = '/login';

  /**
   * Create a new instance.
   *
   * @param {Router} router
   * @param {JwtService} jwtService
   */
  constructor(private router: Router, private jwtService: JwtService) {
    //
  }

  /**
   * Determine if the route can be activated.
   *
   * @return {boolean}
   */
  canActivate(): boolean {
    if (!this.jwtService.exists()) {
      this.router.navigate([OnlyLoggedInGuard.REDIRECT_TO]);

      return false;
    }

    return true;
  }
}
