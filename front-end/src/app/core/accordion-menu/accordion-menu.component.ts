import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { NgbDropdown} from '@ng-bootstrap/ng-bootstrap';
import {animate, transition} from '@angular/animations';

@Component({
  selector: 'accordion-menu',
  templateUrl: './accordion-menu.component.html',
  styleUrls: ['./accordion-menu.component.scss'],
  animations: [
    transition('open => closed', [
      animate('1s')
    ]),
  ]

})


export class AccordionMenuComponent implements OnInit {
  public isCollapsed = false;

  ngOnInit() {
  }
}


