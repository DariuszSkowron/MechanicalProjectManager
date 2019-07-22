import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { CreateProjectComponent } from './create-project/create-project.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { SearchProjectComponent } from './search-project/search-project.component';
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {CoreModule} from "./core/core.module";
import {AboutComponent } from './about/about.component';
import { MechanicalProcessingComponent } from './mechanical-processing/mechanical-processing.component';
import { ProcessedPartComponent } from './processed-parts/processed-part/processed-part.component';
import { NoteTextFilterPipe } from './shared/note-text-filter.pipe';
import {ProcessedPartsComponent} from "./processed-parts/processed-parts.component";


@NgModule({
  declarations: [
    AppComponent,
    CreateProjectComponent,
    ProjectDetailsComponent,
    ProjectListComponent,
    SearchProjectComponent,
    AboutComponent,
    MechanicalProcessingComponent,
    ProcessedPartComponent,
    NoteTextFilterPipe,
    ProcessedPartsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
