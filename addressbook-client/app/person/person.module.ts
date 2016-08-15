import {NgModule}      from '@angular/core';
import {CommonModule} from "@angular/common";
import {FormsModule} from '@angular/forms';
import {HttpModule, JsonpModule} from '@angular/http';
import {PersonListComponent} from "./person-list.component";
import {PersonService} from "./person-service";
import {PersonDetailComponent} from "./person-detail.component";
import {PersonCreateComponent} from "./person-create.component";

@NgModule({
  imports:      [ CommonModule, FormsModule, HttpModule, JsonpModule ],
  declarations: [ PersonListComponent, PersonDetailComponent, PersonCreateComponent ],
  exports: [PersonListComponent, PersonDetailComponent, PersonCreateComponent],
  providers: [PersonService]
})
export class PersonModule { }
