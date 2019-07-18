import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FooterComponent} from "./footer/footer.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {ErrorComponent} from "./error/error.component";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent,
    ErrorComponent
  ],
  exports: [
    NavbarComponent,
    FooterComponent
  ],

  imports: [
    CommonModule,
    RouterModule
  ],
})
export class CoreModule { }
