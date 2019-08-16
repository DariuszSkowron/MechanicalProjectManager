import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { CreateProjectComponent } from './project/create-project/create-project.component';
import { ProjectDetailsComponent } from './project/project-details/project-details.component';
import { ProjectListComponent } from './project/project-list/project-list.component';
import { SearchProjectComponent } from './project/search-project/search-project.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {CoreModule} from './core/core.module';
import {AboutComponent } from './about/about.component';
import { ProcessedPartComponent } from './processed-parts/processed-part/processed-part.component';
import { ProcessedPartDwgNumberFilterPipe } from './shared/processed-part-dwg-number-filter.pipe';
import {ProcessedPartsComponent} from './processed-parts/processed-parts.component';
import { CommercialPartsComponent } from './commercial-parts/commercial-parts.component';
import { CommercialPartComponent } from './commercial-parts/commercial-part/commercial-part.component';
import { CommercialPartFilterPipe } from './shared/commercial-part-filter.pipe';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CollapseModule} from 'ngx-bootstrap';



@NgModule({
  declarations: [
    AppComponent,
    CreateProjectComponent,
    ProjectDetailsComponent,
    ProjectListComponent,
    SearchProjectComponent,
    AboutComponent,
    ProcessedPartComponent,
    ProcessedPartDwgNumberFilterPipe,
    ProcessedPartsComponent,
    CommercialPartsComponent,
    CommercialPartComponent,
    CommercialPartFilterPipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule,
    BrowserAnimationsModule,
    CollapseModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
