import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

import {TokenService} from '../services';

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
   * @param {TokenService} tokenService
   */
  constructor(private router: Router, private tokenService: TokenService) {
    //
  }

  /**
   * Determine if the route can be activated.
   *
   * @return {boolean}
   */
  canActivate(): boolean {
    if (this.tokenService.exists()) {
      this.router.navigate([OnlyGuestGuard.REDIRECT_TO]);

      return false;
    }

    return true;
  }
}
