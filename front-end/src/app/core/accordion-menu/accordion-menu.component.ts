import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { NgbDropdown} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'accordion-menu',
  templateUrl: './accordion-menu.component.html',
  styleUrls: ['./accordion-menu.component.scss']
})


export class AccordionMenuComponent implements OnInit {
  public isCollapsed = false;
  ngOnInit() {
  }
}


