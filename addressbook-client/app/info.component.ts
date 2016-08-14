
import {Component} from "@angular/core";

@Component({
  selector: 'info',
  template: `
            <h2>Info</h2>
            <p>An address book in Angular 2</p>
            <p>Version {{version}}</p>
        `
})
export class InfoComponent {
  version:string = '1.0';
}
