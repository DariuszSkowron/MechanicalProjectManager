import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {AccordionConfig} from 'ngx-bootstrap';

export function getAccordionConfig(): AccordionConfig {
  return Object.assign(new AccordionConfig(), { closeOthers: true });
}

@Component({
  selector: 'accordion-menu',
  templateUrl: './accordion-menu.component.html',
  styleUrls: ['./accordion-menu.component.scss'],
  providers: [{provide: AccordionConfig, useFactory: getAccordionConfig}]
})





export class AccordionMenuComponent implements OnInit {
  customClass =  'customClass'
  public isCollapsed = false;
  public isCollapsed2 = false;

  ngOnInit() {
  }
}


