import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MechanicalProcessing} from "./model/mechanical-processing";
import {ProcessedPart} from "./model/processed-part";
import {ProjectService} from "../project.service";

@Component({
  selector: 'processed-parts',
  templateUrl: './processed-parts.component.html',
  styleUrls: ['./processed-parts.component.scss']
})
export class ProcessedPartsComponent implements OnInit {
  mechanicalProcessings: MechanicalProcessing[] = [];
  processedParts: ProcessedPart[] = [];
  selectedMechanicalProcessing: MechanicalProcessing;
  nameSearch: string;
  mainProcessSearch: string;
  mainProcesses: ['CNC', 'FREZ'];
  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.getAllMechanicalProcessing();
    this.getAllProcessedParts();
  }

  filterPartsByProcessingType(processingType: any){
    this.processedParts.find(part => part.mainProcess == processingType);
  }


  getAllMechanicalProcessing() {
    this.projectService.getAllMechanicalProcessing().subscribe(res => {
        this.mechanicalProcessings = res;
      },
      err => {
        alert(`An error has occurred;`)
      }
    );
  }


  getAllProcessedParts() {
    this.projectService.getAllProcessedParts().subscribe(
      res => {
      },
      err => {
        alert("While downloading the processed parts occurred an error")
      }
    );
  }

  createMechanicalProcessing() {
    let newMechanicalProcessing: MechanicalProcessing = {
      name: 'New mechanical processing',
      id: null,
      numberOfParts: 0
    };

    this.projectService.postMechanicalProcessing(newMechanicalProcessing).subscribe(
      res => {
        newMechanicalProcessing.id = res.id;
        this.mechanicalProcessings.push(newMechanicalProcessing);
      },
      err => {
        alert("An error has occurred while trying to save the mechanical processing list");
      }
    );
  }

  updateMechanicalProcessing(updatedMechanicalProcessing: MechanicalProcessing) {
    this.projectService.postMechanicalProcessing(updatedMechanicalProcessing).subscribe(
      res => {

      },
      err => {
        alert("An error has occured while updating the mechanical processing list");
      }
    );
  }

  deleteMechanicalProcessing(mechanicalProcessing: MechanicalProcessing) {
    if (confirm("You will now delete mechanical processing list, do you want to proceed?")) {
      this.projectService.deleteMechanicalProcessing(mechanicalProcessing.id).subscribe(
        res => {
          let indexOfMechanicalProcessing = this.mechanicalProcessings.indexOf(mechanicalProcessing);
          this.mechanicalProcessings.splice(indexOfMechanicalProcessing, 1);
        },
        err => {
          alert("Failed to delete mechanical processing list");
        }
      );
    }
  }

  deleteProcessedPart(processedPart: ProcessedPart) {
    if (confirm("You will delete this part, do you want to proceed?")) {
      this.projectService.deleteProcessedPart(processedPart.id).subscribe(
        res => {
          let indexOfProcessedPart = this.processedParts.indexOf(processedPart);
          this.processedParts.splice(indexOfProcessedPart, 1);
        },
        err => {
          alert("Failed to delete the part");
        }
      );
    }
  }

  createProcessedPart(mechanicalProcessingId: string) {
    let newProcessedPart: ProcessedPart = {
      id: null,
      drawingNumber: "",
      mainProcess: "",
      material: "",
      manufacturer: "",
      mechanicalProcessingId: mechanicalProcessingId
    };

    this.projectService.saveProcessedPart(newProcessedPart).subscribe(
      res => {
        newProcessedPart.id = res.id;
        this.processedParts.push(newProcessedPart);
      },
      err => {
        alert("An error has occurred while saving part");
      }
    );
  }

  selectMechanicalProcessing(mechanicalProcessing: MechanicalProcessing) {
    this.selectedMechanicalProcessing = mechanicalProcessing;
    this.projectService.getProcessedPartsByMechanicalProcessing(mechanicalProcessing.id).subscribe(
      res => {
        this.processedParts = res;
      },
      err => {
        alert("An error has occurred while fetching the parts4444")
      }
    );
  }

  updateProcessedPart(updatedProcessedPart: ProcessedPart) {
    this.projectService.saveProcessedPart(updatedProcessedPart).subscribe(
      res => {
      },
      err => {
        alert("An error occurred while updating the note");
      }
    );
  }

  selectAllProcessedParts() {
    this.selectedMechanicalProcessing = null;
    this.getAllProcessedParts();
  }

}
