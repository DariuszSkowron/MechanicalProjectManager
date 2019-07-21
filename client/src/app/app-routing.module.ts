import {RouterModule, Routes} from "@angular/router";
import {ProjectListComponent} from "./project-list/project-list.component";
import {CreateProjectComponent} from "./create-project/create-project.component";
import {SearchProjectComponent} from "./search-project/search-project.component";
import {NgModule} from "@angular/core";
import {AboutComponent} from "./about/about.component";
import {MechanicalProcessingComponent} from "./mechanical-processing/mechanical-processing.component";

const routes: Routes = [
  {path: '', redirectTo: 'project', pathMatch: 'full'},
  {path: 'project', component: ProjectListComponent},
  {path: 'add', component: CreateProjectComponent},
  {path: 'findbynumber', component: SearchProjectComponent},
  {path: 'about', component: AboutComponent},
  {path: 'mechanicalProcessing', component: MechanicalProcessingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
