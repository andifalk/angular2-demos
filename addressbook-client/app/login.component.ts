
import {Component} from "@angular/core";
import {AuthenticationService} from "./authentication-service";
@Component({
  selector: 'login',
  template: '<h2>Login</h2>'
})
export class LoginComponent {

  constructor(private _authService:AuthenticationService) {

  }

  login() {
    console.log('Login');
    //this._authService.authenticate();
  }

  ngOnInit() {
    if (window.location.hash) {
      this._authService.tokenCallback();
    }
  }

}
