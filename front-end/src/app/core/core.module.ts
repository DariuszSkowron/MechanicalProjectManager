import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FooterComponent} from './footer/footer.component';
import {NavbarComponent} from './navbar/navbar.component';
import {ErrorComponent} from './error/error.component';
import {RouterModule} from '@angular/router';
import { AccordionMenuComponent } from './accordion-menu/accordion-menu.component';

@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent,
    ErrorComponent,
    AccordionMenuComponent
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    AccordionMenuComponent
  ],

  imports: [
    CommonModule,
    RouterModule
  ],
})
export class CoreModule {
}
