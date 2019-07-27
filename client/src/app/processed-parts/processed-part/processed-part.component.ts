import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MechanicalProcessing} from "../model/mechanical-processing";
import {ProcessedPart} from "../model/processed-part";
import {ProjectService} from "../../project.service";

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

  updateProcessedPart1(){
    this.processedPartUpdated.emit(this.processedPart);
  }

  deleteProcessedPart1(){
    this.processedPartDeleted.emit(this.processedPart);
  }
}
