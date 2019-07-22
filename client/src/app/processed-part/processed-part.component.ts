import {Component, OnInit} from '@angular/core';
import {MechanicalProcessing} from "../model/mechanical-processing";
import {ProcessedPart} from "../model/processed-part";
import {ProjectService} from "../project.service";

@Component({
  selector: 'processed-part',
  templateUrl: './processed-part.component.html',
  styleUrls: ['./processed-part.component.scss']
})
export class ProcessedPartComponent implements OnInit {
  mechanicalProcessing: MechanicalProcessing[] = [];
  processedParts: ProcessedPart[] = [];
  selectedMechanicalProcessing: MechanicalProcessing;
  searchText: string;

  constructor(private projectService: ProjectService) {
  }

  ngOnInit() {
    this.getAllMechanicalProcessing();
    this.getAllProcessedParts();
  }

  public getAllMechanicalProcessing() {
    this.projectService.getAllMechanicalProcessing().subscribe(res => {
        this.mechanicalProcessing = res;
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
        this.mechanicalProcessing.push(newMechanicalProcessing);
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
          let indexOfMechanicalProcessing = this.mechanicalProcessing.indexOf(mechanicalProcessing);
          this.mechanicalProcessing.splice(indexOfMechanicalProcessing, 1);
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
        alert("An error has occured while saving part");
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
        alert("An error has occurred while fetching the parts")
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
