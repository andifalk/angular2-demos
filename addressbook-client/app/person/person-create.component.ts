import {Component} from "@angular/core";
import {Person} from "./person";
import {PersonService} from "./person-service";
import {Router} from "@angular/router";

@Component({
    selector: 'person-create-form',
    templateUrl: 'app/person/person-create.component.html',
    styles: [`.ng-valid[required] {
                border-left: 5px solid #42A948; /* green */
              }

              .ng-invalid {
                border-left: 5px solid #a94442; /* red */
              }`
    ]
  })
export class PersonCreateComponent {

  public person:Person;
  genders: string[];
  selectedGender:string;
  submitted = false;
  errorMessage:string;

  constructor(private _personService:PersonService, private _router: Router) {
    this.person = new Person(0, 'MALE', '', '', null);
    this.genders = [];
    this.genders.push('');
    this.genders.push('MALE');
    this.genders.push('FEMALE');
  }

  resetPerson() {
    this.person = new Person(0, 'MALE', '', '', null);
  }

  onSubmit() {
    this.submitted = true;
    console.log('Submitted person=%o', this.person);
    this._personService
      .addPerson(this.person)
      .subscribe(
        person => this._router.navigate(['/person-list']),
        error => this.errorMessage = error
      );
  }

  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.person); }

}
