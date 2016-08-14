import { NgModule }      from '@angular/core';
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {PersonListComponent} from "./person-list.component";
import {PersonService} from "./person-service";
import {PersonDetailComponent} from "./person-detail.component";

@NgModule({
  imports:      [ CommonModule, FormsModule ],
  declarations: [ PersonListComponent, PersonDetailComponent ],
  exports: [PersonListComponent, PersonDetailComponent],
  providers: [PersonService]
})
export class PersonModule { }
