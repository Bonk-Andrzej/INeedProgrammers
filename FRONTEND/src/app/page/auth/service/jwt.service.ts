import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class JwtService {
  /**
   * @var {string}
   */
  private static get STORAGE_KEY(): string {
    return `${environment.version.toLowerCase()}_access_token`;
  }

  /**
   * @var {string}
   */
  private token: string = '';

  /**
   * Get the token's value.
   *
   * @return {string}
   */
  get(): string {
    this.fetch();

    return this.token;
  }

  /**
   * Get decoded token's value.
   *
   * @return {Object}
   */
  decoded(): Object {
    const base64Url = this.get().split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');

    return JSON.parse(window.atob(base64));
  }

  /**
   * Set the token value.
   *
   * @param {string} value
   * @return {void}
   */
  set(value: string): void {
    this.token = value;

    localStorage.setItem(JwtService.STORAGE_KEY, value);
  }

  /**
   * Returns whether the token exists.
   *
   * @return {boolean}
   */
  exists(): boolean {
    this.fetch();

    return !!this.token;
  }

  /**
   * Remove the token.
   *
   * @return {void}
   */
  clear(): void {
    this.token = null;

    localStorage.removeItem(JwtService.STORAGE_KEY);
  }

  /**
   * Set the token's value from the storage.
   *
   * @return {void}
   */
  private fetch(): void {
    this.token = localStorage.getItem(JwtService.STORAGE_KEY) || '';
  }
}
