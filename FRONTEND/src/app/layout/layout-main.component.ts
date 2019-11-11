import {Component} from '@angular/core';

@Component({
  selector: 'layout-main',
  template: `
    <ng-content></ng-content>
  `,
  styles: [
    `
      :host {
        flex-grow: 1;
      }
    `
  ]
})
export class LayoutMainComponent {
  /**ś
   * Create a new instance.
   */
  constructor() {
    //
  }
}
