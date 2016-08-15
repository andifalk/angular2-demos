import {Component} from "@angular/core";
import {Person} from "./person";
import {DataTable} from 'primeng/primeng';
import {Column} from 'primeng/primeng';
import {PersonService} from "./person-service";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
    selector: 'person-detail',
    templateUrl: 'app/person/person-detail.component.html',
    directives: [DataTable, Column]
  })
export class PersonDetailComponent {

  public person:Person;

  constructor(private _personService:PersonService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];
      this._personService.getPerson(id)
        .subscribe(
          pers => { console.log(pers); this.person = pers; console.log('this.person=%o', this.person)}
        );
        //.then(pers => { console.log(pers); this.person = pers; console.log('this.person=%o', this.person)});
    });
  }

}
