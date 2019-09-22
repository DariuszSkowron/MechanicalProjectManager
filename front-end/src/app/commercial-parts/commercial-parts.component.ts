import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  OnChanges,
  OnInit,
  SimpleChange,
  SimpleChanges
} from '@angular/core';
import {PartsOrder} from './model/parts-order';
import {CommercialPart} from './model/commercial-part';
import {ApiService} from '../shared/api.service';
import {Project} from '../project/project';
import {Invoice} from './model/invoice';
import {Manufacturer} from "../manufacturers/model/manufacturer";


@Component({
  selector: 'commercial-parts',
  templateUrl: './commercial-parts.component.html',
  styleUrls: ['./commercial-parts.component.scss']
})
export class CommercialPartsComponent implements OnInit {
  partsOrders: PartsOrder[] = [];
  projects: Project[] = [];
  commercialParts: CommercialPart[] = [];
  commercialPart: CommercialPart;
  invoices: Invoice[] = [];
  partsOrder: PartsOrder;
  selectedPartsOrder: PartsOrder;
  nameOrOrderSymbolSearch: string;
  manufacturerSearch: string;
  index = 1;
  todaysDate: Date = new Date();
  selectedCommercialParts: Array<any>;
  existingManufacturers: Array<any>;

  constructor(private projectService: ApiService) {
  }

  ngOnInit() {
    this.getAllPartsOrders();
    this.getAllCommercialParts();
    this.getAllProjects();
    this.getAllSelected();
  }

  getAllSelected() {
    this.selectedCommercialParts = this.commercialParts.filter(commercial => commercial.checked === true);
  }

  getAllProjects() {
    this.projectService.getProjectList().subscribe(res => {
        this.projects = res;
      },
      err => {
        alert(`An error has occurred` + err);
      }
    );
  }

  filterPartsByManufacturer() {
    this.existingManufacturers = this.commercialParts.map(commercialPart => commercialPart.manufacturer);
  }

  getAllPartsOrders() {
    this.projectService.getAllPartsOrders().subscribe(res => {
        this.partsOrders = res;
      },
      err => {
        alert(`An error has occurred` + err);
      }
    );
  }


  getAllCommercialParts() {
    this.projectService.getAllCommercialParts().subscribe(
      res => {
        this.commercialParts = res;
      },
      err => {
        alert('While downloading the parts orders occurred an error');
      }
    );
  }

  createPartsOrder() {
    const newPartsOrder: PartsOrder = {
      name: 'Parts order No.' + this.index,
      id: null,
      numberOfParts: 0,
      project: null,
      projectId: null
    };

    this.projectService.postPartsOrder(newPartsOrder).subscribe(
      res => {
        newPartsOrder.id = res.id;
        this.partsOrders.push(newPartsOrder);
        this.index = this.index + 1;
      },
      err => {
        alert('An error has occurred while trying to save the parts order list');
      }
    );
  }

  updatePartsOrder(updatePartsOrder: PartsOrder) {
    this.projectService.postPartsOrder(updatePartsOrder).subscribe(
      res => {
        this.getAllProjects();
      },
      err => {
        alert('An error has occured while updating the mechanical processing list');
      }
    );
  }

  updateProject(updatePartsOrder: PartsOrder) {
    this.projectService.postPartsOrder(updatePartsOrder).subscribe(
      res => {

      },
      err => {
        alert('An error has occured while updating the mechanical processing list');
      }
    );
  }

  deletePartsOrder(partsOrder: PartsOrder) {
    if (confirm('You will now delete parts order list, do you want to proceed?')) {
      this.projectService.deletePartsOrder(partsOrder.id).subscribe(
        res => {
          const indexOfPartsOrder = this.partsOrders.indexOf(partsOrder);
          this.partsOrders.splice(indexOfPartsOrder, 1);
          this.selectedPartsOrder = null;
          this.getAllCommercialParts();
        },
        err => {
          alert('Failed to delete parts order list');
        }
      );
    }
  }

  deleteCommercialPart(commercialPart: CommercialPart) {
    if (confirm('You will delete this part, do you want to proceed?')) {
      this.projectService.deleteCommercialPart(commercialPart.id).subscribe(
        res => {
          const indexOfCommercialPart = this.commercialParts.indexOf(commercialPart);
          this.commercialParts.splice(indexOfCommercialPart, 1);
        },
        err => {
          alert('Failed to delete the part');
        }
      );
    }
  }

  createCommercialPart(partsOrderId: string) {
    const newCommercialPart: CommercialPart = {
      id: null,
      type: '',
      orderSymbol: '',
      name: '',
      manufacturer: 'UNASSIGNED',
      quantity: '',
      orderDate: this.todaysDate,
      deliveryDate: null,
      price: '',
      manufacturerId: '1',
      partsOrderId: partsOrderId,
      invoiceId: null,
    };

    this.projectService.saveCommercialPart(newCommercialPart).subscribe(
      res => {
        newCommercialPart.id = res.id;
        this.commercialParts.push(newCommercialPart);
      },
      err => {
        alert('An error has occurred while saving part');
      }
    );
  }

  selectPartsOrder(partsOrder: PartsOrder) {
    this.selectedPartsOrder = partsOrder;
    this.projectService.getCommercialPartsByPartsOrder(partsOrder.id).subscribe(
      res => {
        this.commercialParts = res;
      },
      err => {
        alert('An error has occurred while fetching the parts');
      }
    );
  }

  updateCommercialParts(updatedCommercialPart: CommercialPart) {
    this.projectService.saveCommercialPart(updatedCommercialPart).subscribe(
      res => {
        this.getAllSelected();
      },
      err => {
        alert('An error occurred while updating the part' + JSON.stringify(err));
      }
    );
  }


  selectAllCommercialParts() {
    this.selectedPartsOrder = null;
    this.getAllCommercialParts();
  }

  updatecc() {
    this.projectService.getCommercialPartsByPartsOrder(this.selectedPartsOrder.id).subscribe(
      res => {
        this.commercialParts = res;
      }
    );
  }


  generateInvoice() {
    const newInvoice: Invoice = {
      id: null,
      commercialParts: this.commercialParts.filter(commercial => commercial.checked === true).map(commercial => commercial.id)
    };

    if (confirm('You will create new invoice from selected parts, do you want to continue?')) {
      this.projectService.postInvoice(newInvoice).subscribe(
        res => {
          newInvoice.id = res.id;
          this.invoices.push(newInvoice);
          this.updatecc();
        },
        err => {
          alert('An error has occurred while saving part'),
          console.log('oops', err.err);
          console.log(JSON.stringify(err));
        }
      );
    }
  }

  generateInvoiceForSpecifiedManufacturer(manufacturer: string) {
    const newInvoice: Invoice = {
      id: null,
      commercialParts: this.commercialParts
        .filter(commercial => commercial.manufacturer === manufacturer)
        .map(commercial => commercial.id)
    };

    if (confirm('You will now create new invoice containing all yet unordered parts from manufacturer - ' + manufacturer)) {
      this.projectService.postInvoice(newInvoice).subscribe(
        res => {
          newInvoice.id = res.id;
          this.invoices.push(newInvoice);
          this.updatecc();
        },
        err => {
          alert('An error has occurred while saving part'),
            console.log('oops', err.err);
          console.log(JSON.stringify(err));
        }
      );
    }
  }


}
