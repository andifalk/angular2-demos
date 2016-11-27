import {Component} from "@angular/core";
import {Person} from "./person";
import {PersonService} from "./person-service";
import {Router} from "@angular/router";
import {Message} from "primeng/components/common/api";

@Component({
    selector: 'person-list',
    templateUrl: 'app/person/person-list.component.html'
  })
export class PersonListComponent {

  public persons:Array<Person>;

  public errorMessage:string;

  msgs: Message[] = [];

  constructor(private _personService:PersonService, private router:Router) {
    this._personService.getPersons().subscribe(
      persons => this.persons = persons.persons,
      error => {this.msgs = [];
        this.msgs.push({severity:'error', summary:error, detail:'Error loading data'});},
      () => console.log('complete')
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
