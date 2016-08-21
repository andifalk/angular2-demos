
import {Injectable} from "@angular/core";

@Injectable()
export class AuthenticationService {

  private _storage = sessionStorage;

  public getBasicAuthorizationHeader():string {
    let value:string = 'Basic ' + this.encodeBase64('bob:secret');
    //console.log('Auth header value=%o', value);
    return value;
  }

  public getBearerAuthorizationHeader():string {
    let value:string = 'Bearer ' + this.getAccessToken();
    //console.log('Auth header value=%o', value);
    return value;
  }

  // ucs-2 string to base64 encoded ascii
  private encodeBase64(str:string):string {
    return window.btoa(str);
  }

  // base64 encoded ascii to ucs-2 string
  private decodeBase64(str:string):string {
    return window.atob(str);
  }

  public authorize() {
    let url = 'http://localhost:9090/oauth/authorize?' +
      'response_type=' + encodeURI('token') + '&' +
      'client_id=' + encodeURI('addressbook') + '&' +
      'redirect_uri=' + encodeURI('http://localhost:3000/login') + '&' +
      'scope=' + encodeURI('address') + '&' +
      'state=' + encodeURI('aaaaabbbb123');

    console.debug('AuthenticationService#authorization url=%o', url);

    window.location.href = url;
  }

  public tokenCallback() {
    let hash = window.location.hash.substr(1);

    let result: any = hash.split('&').reduce(function (result, item) {
      let parts = item.split('=');
      result[parts[0]] = parts[1];
      return result;
    }, {});

    console.debug('AuthenticationService - result from callback %o', result);

    let accesstoken:string = result.access_token;

    this._storage.setItem('access_token', accesstoken);

  }

  public getAccessToken():string {
    return this._storage.getItem('access_token');
  }

}
