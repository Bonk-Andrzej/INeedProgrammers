import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

import {TokenService} from '../services';

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
    if (!this.tokenService.exists()) {
      this.router.navigate([OnlyLoggedInGuard.REDIRECT_TO]);

      return false;
    }

    return true;
  }
}
