import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {JwtService} from "../service/jwt.service";


@Injectable({
  providedIn: 'root'
})
export class OnlyGuestGuard implements CanActivate {
  /**
   * @var {string}
   */
  private static readonly REDIRECT_TO: string = '/panel';

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
    if (this.jwtService.exists()) {
      this.router.navigate([OnlyGuestGuard.REDIRECT_TO]);

      return false;
    }

    return true;
  }
}
