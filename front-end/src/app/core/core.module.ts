import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FooterComponent} from './footer/footer.component';
import {NavbarComponent} from './navbar/navbar.component';
import {ErrorComponent} from './error/error.component';
import {RouterModule} from '@angular/router';
import { AccordionMenuComponent } from './accordion-menu/accordion-menu.component';
import {AccordionModule, CollapseModule} from "ngx-bootstrap";
import { ManufuacturersComponent } from './manufuacturers/manufuacturers.component';


@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent,
    ErrorComponent,
    AccordionMenuComponent,
    ManufuacturersComponent
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    AccordionMenuComponent
  ],

  imports: [
    CommonModule,
    RouterModule,
    CollapseModule,
    AccordionModule,
  ],
})
export class CoreModule {
}
