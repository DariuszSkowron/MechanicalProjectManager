import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProcessedPart} from "../model/processed-part";

@Component({
  selector: 'processed-part',
  templateUrl: './processed-part.component.html',
  styleUrls: ['./processed-part.component.scss']
})
export class ProcessedPartComponent implements OnInit {

  @Input() processedPart: ProcessedPart;
  @Output() processedPartUpdated: EventEmitter<ProcessedPart> = new EventEmitter<ProcessedPart>();
  @Output() processedPartDeleted: EventEmitter<ProcessedPart> = new EventEmitter<ProcessedPart>();

  constructor() {
  }

  ngOnInit() {
  }

  updateProcessedPart(){
    this.processedPartUpdated.emit(this.processedPart);
  }

  deleteProcessedPart(){
    this.processedPartDeleted.emit(this.processedPart);
  }
}
