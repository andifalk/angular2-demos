import {Component} from "@angular/core";
import {Person} from "./person";
import {DataTable} from 'primeng/primeng';
import {Column} from 'primeng/primeng';
import {Button} from 'primeng/primeng';
import {PersonService} from "./person-service";
import {Router} from "@angular/router";
//import './rxjs-operators';

@Component({
    selector: 'person-list',
    templateUrl: 'app/person/person-list.component.html',
    directives: [DataTable, Column, Button]
  })
export class PersonListComponent {

  public persons:Array<Person>;

  public errorMessage:string;

  constructor(private _personService:PersonService, private router:Router) {
    this._personService.getPersons().subscribe(
      persons => this.persons = persons.persons,
      error =>  this.errorMessage = <any>error
    );
  }

  showPerson(person:Person) {
    console.log(person);
    this.router.navigate(['/person', person.id]);
  }

  editPerson(person:Person) {
    console.log(person);
    this.router.navigate(['/person', person.id]);
  }

  createPerson() {
    this.router.navigate(['/person-create'])
  }
}
