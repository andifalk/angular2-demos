import {Component} from "@angular/core";
import {Person} from "./person";
import {DataTable} from 'primeng/primeng';
import {Column} from 'primeng/primeng';
import {Button} from 'primeng/primeng';
import {PersonService} from "./person-service";
import {Router} from "@angular/router";

@Component({
    selector: 'person-list',
    templateUrl: 'app/person/person-list.component.html',
    directives: [DataTable, Column, Button]
  })
export class PersonListComponent {

  public persons:Array<Person>;

  constructor(private _personService:PersonService, private router:Router) {
    this._personService.getPersons().then(persons => this.persons = persons);
  }

  showPerson(person:Person) {
    console.log(person);
    this.router.navigate(['/person', person.id]);
  }

  editPerson(person:Person) {
    console.log(person);
    this.router.navigate(['/person', person.id]);
  }
}
