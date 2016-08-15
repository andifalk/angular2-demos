
import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Person} from "./person";
import {Observable } from 'rxjs/Observable';
import {PersonList} from "./person-list";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class PersonService {

  constructor(private _http:Http) {
  }

  private _persons: Array<Person>;

  public getPersons():Observable<PersonList> {
    return this._http
      .get('http://localhost:8080/api/persons')
      .map(this.extractData)
      .catch(this.handleError);
  }

  public addPerson(person:Person):Observable<Person> {
    let body = JSON.stringify({ firstName: person.firstName, lastName: person.lastName, gender: person.gender });
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this._http.post('http://localhost:8080/api/persons', body, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  public getPerson(id: number):Observable<Person> {
    console.log('getPerson() id=%o', id);
    return this._http
      .get('http://localhost:8080/api/persons/' + id)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }

  private handleError (error: any) {
    // In a real world app, we might use a remote logging infrastructure
    // We'd also dig deeper into the error to get a better message
    let errMsg = (error.message) ? error.message :
      error.status ? '${error.status} - ${error.statusText}' : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }
}
