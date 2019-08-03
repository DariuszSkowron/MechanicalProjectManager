import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommercialPart} from '../model/commercial-part';

@Component({
  selector: 'commercial-part',
  templateUrl: './commercial-part.component.html',
  styleUrls: ['./commercial-part.component.scss']
})
export class CommercialPartComponent implements OnInit {
  @Input() commercialPart: CommercialPart;
  @Output() commercialPartUpdated: EventEmitter<CommercialPart> = new EventEmitter<CommercialPart>();
  @Output() commercialPartDeleted: EventEmitter<CommercialPart> = new EventEmitter<CommercialPart>();

  constructor() {
  }

  ngOnInit() {
  }

  updateCommercialPart() {
    this.commercialPartUpdated.emit(this.commercialPart);
  }

  deleteCommercialPart() {
    this.commercialPartDeleted.emit(this.commercialPart);
  }
}
