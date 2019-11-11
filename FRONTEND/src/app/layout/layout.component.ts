import {Component, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'layout',
  template: `
    <div class="min-h-screen flex flex-col">
      <ng-content></ng-content>
    </div>
  `,
  styleUrls: ['./layout.component.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    id: 'layout'
  }
})
export class LayoutComponent {
  /**ś
   * Create a new instance.
   */
  constructor() {
    //
  }
}
