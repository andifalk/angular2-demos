import { Component } from '@angular/core';

@Component({
    selector: 'addressbook-app',
    template: `<h1>Addressbook App</h1>
              <nav>
                <a routerLink="/person-list" routerLinkActive="active">Addressbook</a>
                <a routerLink="/info" routerLinkActive="active">About</a>
              </nav>
              <router-outlet></router-outlet>`
})
export class AppComponent { }