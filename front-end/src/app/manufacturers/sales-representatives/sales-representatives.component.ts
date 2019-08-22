import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SalesRepresentative} from '../model/sales-representative';

@Component({
  selector: 'sales-representatives',
  templateUrl: './sales-representatives.component.html',
  styleUrls: ['./sales-representatives.component.scss']
})
export class SalesRepresentativesComponent implements OnInit {

  @Input() salesRepresentative: SalesRepresentative;
  @Output() salesRepresentativeUpdated: EventEmitter<SalesRepresentative> = new EventEmitter<SalesRepresentative>();
  @Output() salesRepresentativeDeleted: EventEmitter<SalesRepresentative> = new EventEmitter<SalesRepresentative>()

  constructor() { }

  ngOnInit() {
  }

  updateSalesRepresentative() {
    this.salesRepresentativeUpdated.emit(this.salesRepresentative);
  }

  deleteSalesRepresentative() {
    this.salesRepresentativeDeleted.emit(this.salesRepresentative);
  }

}
