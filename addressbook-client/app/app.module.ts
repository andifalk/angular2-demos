import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { routing, appRoutingProviders } from './app.routing';
import { AppComponent }  from './app.component';
import {PersonModule} from "./person/person.module";
import {PageNotFoundComponent} from "./page-not-found.component";
import {InfoComponent} from "./info.component";
import {AuthenticationService} from "./authentication-service";
import {HomeComponent} from "./home.component";
import {LoginComponent} from "./login.component";

@NgModule({
  imports: [ BrowserModule, routing, PersonModule ],
  declarations: [ AppComponent, PageNotFoundComponent, InfoComponent, HomeComponent, LoginComponent ],
  providers: [appRoutingProviders, AuthenticationService],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
