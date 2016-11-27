
import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Person} from "./person";
import {Observable } from 'rxjs/Observable';
import {PersonList} from "./person-list";
import {AuthenticationService} from "../authentication-service";

@Injectable()
export class PersonService {

  constructor(private _http:Http, private _authService:AuthenticationService) {
  }

  private _persons: Array<Person>;

  private _baseurl:string = 'http://localhost:8080';

  public getPersons():Observable<PersonList> {

    let headers = new Headers({ 'Accept': 'application/json' });
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', this._authService.getBearerAuthorizationHeader());
    let options = new RequestOptions({ headers: headers, withCredentials: true });

    return this._http
      .get(this._baseurl + '/persons', options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  public addPerson(person:Person):Observable<Person> {

    let body = JSON.stringify({ firstName: person.firstName, lastName: person.lastName, gender: person.gender });

    let headers = new Headers({ 'Content-Type': 'application/json' });
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', this._authService.getBearerAuthorizationHeader());

    let options = new RequestOptions({ headers: headers, withCredentials: true });

    return this._http.post(this._baseurl + '/persons', body, options).retry(2)
      .map(this.extractData)
      .catch(this.handleError);
  }

  public getPerson(id: number):Observable<Person> {
    console.log('getPerson() id=%o', id);

    let headers = new Headers({ 'Accept': 'application/json' });
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', this._authService.getBearerAuthorizationHeader());
    let options = new RequestOptions({ headers: headers, withCredentials: true });

    return this._http
      .get(this._baseurl + '/persons/' + id, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    console.log('Response=%o', res);
    switch (res.status) {
      case 400:
        throw new Error('Bad Request');
      case 401:
        throw new Error('Unauthenticated');
      case 403:
        throw new Error('Unauthorized');
      case 500:
        throw new Error('Server error');
      default:
        if (res.status < 200 || res.status >= 400) {
          throw new Error('Unknown error');
        }
    }
    let body = res.json();
    return body || { };
  }

  private handleError (error: any) {

    console.warn('Error %o', error);

    let errMsg:any;
    if (error instanceof Response) {
      let response:Response = <Response> error;
      switch (response.status) {
        case 400:
          errMsg = 'Bad Request';
          break;
        case 401:
          errMsg = 'Unauthenticated';
          break;
        case 403:
          errMsg = 'Unauthorized';
          break;
        case 500:
          errMsg = 'Server error';
          break;
        default:
          errMsg = 'Unknown error';
      }
    } else {
        errMsg = (error.message) ? error.message :
        error.status ? error.status + error.statusText : 'Server error';
    }

    return Observable.throw(errMsg);
  }

}
