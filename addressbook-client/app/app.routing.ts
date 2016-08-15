import { Routes, RouterModule } from '@angular/router';
import {PersonListComponent} from "./person/person-list.component";
import {PageNotFoundComponent} from "./page-not-found.component";
import {PersonDetailComponent} from "./person/person-detail.component";
import {InfoComponent} from "./info.component";
import {PersonCreateComponent} from "./person/person-create.component";

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/person-list',
    pathMatch: 'full'
  },
  { path: 'person-list', component: PersonListComponent },
  { path: 'person/:id', component: PersonDetailComponent },
  { path: 'person-create', component: PersonCreateComponent },
  { path: 'info', component: InfoComponent },
  { path: '**', component: PageNotFoundComponent }
];

export const appRoutingProviders: any[] = [

];

export const routing = RouterModule.forRoot(appRoutes);
