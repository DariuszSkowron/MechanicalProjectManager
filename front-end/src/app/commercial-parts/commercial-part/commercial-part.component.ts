import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output, SimpleChanges
} from '@angular/core';
import {CommercialPart} from '../model/commercial-part';
import {Manufacturer} from '../../manufacturers/model/manufacturer';
import {ApiService} from '../../shared/api.service';

@Component({
  selector: 'commercial-part',
  templateUrl: './commercial-part.component.html',
  styleUrls: ['./commercial-part.component.scss']
})
export class CommercialPartComponent implements  OnInit, OnChanges {
  manufacturers: Manufacturer[] = [];
  @Input() commercialPart: CommercialPart;
  @Output() commercialPartUpdated: EventEmitter<any> = new EventEmitter<any>();
  @Output() commercialPartDeleted: EventEmitter<CommercialPart> = new EventEmitter<CommercialPart>();
  @Output() commercialPartSelected: EventEmitter<CommercialPart> = new EventEmitter<CommercialPart>();

  constructor(private apiService: ApiService) {
  }



  ngOnInit() {
    this.getAllManufacturers();
  }

  getAllManufacturers() {
    this.apiService.getAllManufacturers().subscribe(res => {
        this.manufacturers = res;
      },
      err => {
        alert(`An error has occurred` + err);
      }
    );
  }

  updateCommercialPart() {
    this.commercialPartUpdated.emit(this.commercialPart);
  }

  deleteCommercialPart() {
    this.commercialPartDeleted.emit(this.commercialPart);
  }

  selectCommercialPart() {
    this.commercialPart.checked = true;
    this.commercialPartUpdated.emit(this.commercialPart);
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnChanges,, previous CommercialPart: ', changes.commercialPart.previousValue);
    console.log('ngOnChanges,, next CommercialPart: ', changes.commercialPart.currentValue);
    console.log('ngOnChanges,, previous CommercialPart: ', changes.commercialPart.isFirstChange());
  }
}
