import { Component } from '@angular/core';
import './rxjs-operators';

@Component({
    selector: 'addressbook-app',
    template: `<h1>Addressbook App</h1>
              <nav>
                <a routerLink="/home" routerLinkActive="active">Home</a>
                <a routerLink="/person-list" routerLinkActive="active">Addressbook</a>
                <a routerLink="/info" routerLinkActive="active">About</a>
              </nav>
              <router-outlet></router-outlet>`
})
export class AppComponent { }
