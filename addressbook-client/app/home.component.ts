

import {Component} from "@angular/core";
import {AuthenticationService} from "./authentication-service";
@Component({
  selector: 'home',
  template: `<h2>Addressbook Application</h2>
             <p>Please login first</p>
            <a (click)="authorize()">Login (OAuth2)</a>
`
})
export class HomeComponent {

  constructor(private _authService:AuthenticationService) {}

  public authorize() {
    this._authService.authorize();
  }

}
