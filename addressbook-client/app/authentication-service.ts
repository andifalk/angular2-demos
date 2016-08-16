
import {Injectable} from "@angular/core";

@Injectable()
export class AuthenticationService {

  public getAuthorizationHeader():string {
    let value:string = 'Basic ' + this.encodeBase64('bob:secret');
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

}
