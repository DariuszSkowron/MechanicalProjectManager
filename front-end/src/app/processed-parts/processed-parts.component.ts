import {Component, OnInit} from '@angular/core';
import {MechanicalProcessing} from './model/mechanical-processing';
import {ProcessedPart} from './model/processed-part';
import {ApiService} from '../shared/api.service';

@Component({
  selector: 'processed-parts',
  templateUrl: './processed-parts.component.html',
  styleUrls: ['./processed-parts.component.scss']
})
export class ProcessedPartsComponent implements OnInit {
  mechanicalProceedings: MechanicalProcessing[] = [];
  processedParts: ProcessedPart[] = [];
  selectedMechanicalProcessing: MechanicalProcessing;
  nameSearch: string;
  mainProcessSearch: string;
  defaultMachining: any;

  constructor(private projectService: ApiService) { }

  ngOnInit() {
    this.defaultMachining = this.processedParts[0];
    this.getAllMechanicalProcessing();
    this.getAllProcessedParts();
  }

  filterPartsByProcessingType(processingType: any) {
    this.processedParts.find(part => part.mainProcess === processingType);
  }

  getAllMechanicalProcessing() {
    this.projectService.getAllMechanicalProcessing().subscribe(res => {
        this.mechanicalProceedings = res;
      },
      err => {
        alert(`An error has occurred` + err);
      }
    );
  }


  getAllProcessedParts() {
    this.projectService.getAllProcessedParts().subscribe(
      res => {
        console.log(res);
      },
      err => {
        alert('While downloading the processed parts occurred an error');
      }
    );
  }

  createMechanicalProcessing() {
    const newMechanicalProcessing: MechanicalProcessing = {
      name: 'New processing list',
      id: null,
      numberOfParts: 0
    };

    this.projectService.postMechanicalProcessing(newMechanicalProcessing).subscribe(
      res => {
        newMechanicalProcessing.id = res.id;
        this.mechanicalProceedings.push(newMechanicalProcessing);
      },
      err => {
        alert('An error has occurred while trying to save the mechanical processing list');
      }
    );
  }

  updateMechanicalProcessing(updatedMechanicalProcessing: MechanicalProcessing) {
    this.projectService.postMechanicalProcessing(updatedMechanicalProcessing).subscribe(
      res => {

      },
      err => {
        alert('An error has occured while updating the mechanical processing list');
      }
    );
  }

  deleteMechanicalProcessing(mechanicalProcessing: MechanicalProcessing) {
    if (confirm('You will now delete mechanical processing list, do you want to proceed?')) {
      this.projectService.deleteMechanicalProcessing(mechanicalProcessing.id).subscribe(
        res => {
          const indexOfMechanicalProcessing = this.mechanicalProceedings.indexOf(mechanicalProcessing);
          this.mechanicalProceedings.splice(indexOfMechanicalProcessing, 1);
        },
        err => {
          alert('Failed to delete mechanical processing list');
        }
      );
    }
  }

  deleteProcessedPart(processedPart: ProcessedPart) {
    if (confirm('You will delete this part, do you want to proceed?')) {
      this.projectService.deleteProcessedPart(processedPart.id).subscribe(
        res => {
          const indexOfProcessedPart = this.processedParts.indexOf(processedPart);
          this.processedParts.splice(indexOfProcessedPart, 1);
        },
        err => {
          alert('Failed to delete the part');
        }
      );
    }
  }

  createProcessedPart(mechanicalProcessingId: string) {
    const newProcessedPart: ProcessedPart = {
      id: null,
      drawingNumber: '',
      mainProcess: '',
      material: '',
      manufacturer: '',
      readinessOfPart: null,
      partFinished: false,
      mechanicalProcessingId: mechanicalProcessingId
    };

    this.projectService.saveProcessedPart(newProcessedPart).subscribe(
      res => {
        newProcessedPart.id = res.id;
        this.processedParts.push(newProcessedPart);
      },
      err => {
        alert('An error has occurred while saving part');
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
        alert('An error has occurred while fetching the parts');
      }
    );
  }

  updateProcessedPart(updatedProcessedPart: ProcessedPart) {
    this.projectService.saveProcessedPart(updatedProcessedPart).subscribe(
      res => {
      },
      err => {
        alert('An error occurred while updating the part' + JSON.stringify(err));
      }
    );
  }

  selectAllProcessedParts() {
    this.selectedMechanicalProcessing = null;
    this.getAllProcessedParts();
  }

}
