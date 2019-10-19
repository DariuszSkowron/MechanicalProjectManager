import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {Manufacturer} from '../manufacturers/model/manufacturer';
import {SalesRepresentative} from '../manufacturers/model/sales-representative';

@Component({
  selector: 'inquiry',
  templateUrl: './inquiry.component.html',
  styleUrls: ['./inquiry.component.scss']
})
export class InquiryComponent implements OnInit {
  manufacturers: Manufacturer[] = [];
  salesRepresentatives: SalesRepresentative[] = [];
  salesRepresentative: SalesRepresentative;
  model: InquiryViewModel = {
    title: '',
    from: '',
    sendTo: '',
    invoiceId: ''
  };

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.getAllSalesRepresentatives();
  }

  getAllSalesRepresentatives() {
    this.apiService.getAllSalesRepresentatives().subscribe(res => {
        this.salesRepresentatives = res;
      },
      err => {
        alert(`An error has occurred` + err);
      }
    );
  }

  sendInquiry(): void {
    this.apiService.postInquiry(this.model).subscribe(
      res => {
        location.reload();
      },
      err => {
        alert(`Something went wrong, invoice sending failed`);
      }
    );
  }
}

  export interface InquiryViewModel {
    title: string;
    from: string;
    sendTo: string;
    invoiceId: string;
}

