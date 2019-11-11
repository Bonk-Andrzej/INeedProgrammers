import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  /**
   * Create a new instance.
   *
   * @param {Router} router
   */
  constructor(private router: Router) {
    console.log('auth comp')

  }

  ngOnInit() {
    if (this.router.url === '/') {
      this.router.navigate(['/login']);
    }
  }

}
