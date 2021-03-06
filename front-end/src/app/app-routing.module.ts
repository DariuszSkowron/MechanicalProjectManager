import {RouterModule, Routes} from '@angular/router';
import {ProjectListComponent} from './project/project-list/project-list.component';
import {CreateProjectComponent} from './project/create-project/create-project.component';
import {SearchProjectComponent} from './project/search-project/search-project.component';
import {NgModule} from '@angular/core';
import {AboutComponent} from './about/about.component';
import {ProcessedPartsComponent} from './processed-parts/processed-parts.component';
import {CommercialPartsComponent} from './commercial-parts/commercial-parts.component';
import {ManufacturersComponent} from './manufacturers/manufacturers.component';
import {SalesRepresentativesComponent} from './manufacturers/sales-representatives/sales-representatives.component';
import {InquiryComponent} from './inquiry/inquiry.component';

const routes: Routes = [
  {path: '', redirectTo: 'project', pathMatch: 'full'},
  {path: 'project', component: ProjectListComponent},
  {path: 'add', component: CreateProjectComponent},
  {path: 'findByNumber', component: SearchProjectComponent},
  {path: 'about', component: AboutComponent},
  {path: 'processedParts', component: ProcessedPartsComponent},
  {path: 'commercialParts', component: CommercialPartsComponent },
  {path: 'manufacturer', component: ManufacturersComponent },
  {path: 'salesRepresentative', component: SalesRepresentativesComponent },
  {path: 'inquiry', component: InquiryComponent},
  // {path: 'invoice', component: InvoiceComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
