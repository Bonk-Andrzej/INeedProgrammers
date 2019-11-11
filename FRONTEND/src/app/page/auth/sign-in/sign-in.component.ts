import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {AccountService} from "../service/account.service";
import {JwtService} from "../service/jwt.service";
import {finalize, tap} from "rxjs/operators";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  /**
   * @var {boolean}
   */
  isProcessing: boolean = false;

  /**
   * @var {FormGroup}
   */
  form: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required])
  });


  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private jwtService: JwtService,
    private accountService: AccountService,
    private translate: TranslateService
  ) {
    console.log('sign in comp')
  }

  ngOnInit() {
  }


  /**
   * Handle loging to the application.
   *
   * @return void
   */
  signIn(): void {
    this.isProcessing = true;

    this.accountService
      .authorizeUser({
        email: this.form.value.email,
        password: this.form.value.password
      })
      .pipe(
        tap(response => {
          this.jwtService.set(response.id_token);
        }),
        finalize(() => (this.isProcessing = false))
      )
      .subscribe(
        async response => {
          const message = await this.translate
            .get('MESSAGES.signin.success')
            .toPromise();

          this.snackBar.open(message, null);

          this.router.navigate(['technology']);
        },
        async exception => {
          const message = await this.translate
            .get('MESSAGES.signin.failed')
            .toPromise();

          this.snackBar.open(message, null);

          this.form.get('password').reset(null);
        }
      );
  }
}
