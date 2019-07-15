import {RouterModule, Routes} from "@angular/router";
import {ProjectListComponent} from "./project-list/project-list.component";
import {CreateProjectComponent} from "./create-project/create-project.component";
import {SearchProjectComponent} from "./search-project/search-project.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  {path: '', redirectTo: 'project', pathMatch: 'full'},
  {path: 'customer', component: ProjectListComponent},
  {path: 'add', component: CreateProjectComponent},
  {path: 'findbyid', component: SearchProjectComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
