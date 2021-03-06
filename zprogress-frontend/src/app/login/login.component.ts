import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../shared/AuthenticationService';
import {Md5} from 'ts-md5/dist/md5';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private loginForm: FormGroup;
  private error: string;

  constructor(private fb: FormBuilder,
              private authService: AuthenticationService,
              private router: Router) {

    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  authenticate() {
    if (!this.authService.isUserAuthenticated()) {
      const username = this.loginForm.value.username;
      const passwordMd5 = (new Md5()).appendStr(this.loginForm.value.password).end();
      this.authService.authenticate(username, passwordMd5 + '')
        .subscribe(
          res => {
            this.error = null;
            this.router.navigate(['/main']);
          },
          err => {
            console.log(err);
            this.error = 'authentication failed';
          });
    } else {
      console.log('already logged in');
      this.router.navigate(['/main']);
    }
  }

}
