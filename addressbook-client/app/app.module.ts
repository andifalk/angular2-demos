import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { routing, appRoutingProviders } from './app.routing';
import { AppComponent }  from './app.component';
import {PersonModule} from "./person/person.module";
import {PageNotFoundComponent} from "./page-not-found.component";
import {InfoComponent} from "./info.component";

@NgModule({
  imports: [ BrowserModule, routing, PersonModule ],
  declarations: [ AppComponent, PageNotFoundComponent, InfoComponent ],
  providers: [appRoutingProviders],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
