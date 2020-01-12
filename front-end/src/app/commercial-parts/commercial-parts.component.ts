import {AfterViewInit, Component, OnInit, ViewChild,} from '@angular/core';
import {PartsOrder} from './model/parts-order';
import {CommercialPart} from './model/commercial-part';
import {ApiService} from '../shared/api.service';
import {Project} from '../project/project';
import {Invoice} from './model/invoice';
import {MatPaginator, MatTableDataSource} from '@angular/material';


@Component({
  selector: 'commercial-parts',
  templateUrl: './commercial-parts.component.html',
  styleUrls: ['./commercial-parts.component.scss']
})
export class CommercialPartsComponent implements OnInit, AfterViewInit {
  partsOrders: PartsOrder[] = [];
  projects: Project[] = [];
  commercialParts: CommercialPart[] = [];
  commercialPart: CommercialPart;
  invoices: Invoice[] = [];
  partsOrder: PartsOrder;
  selectedPartsOrder: PartsOrder;
  nameOrOrderSymbolSearch: string;
  manufacturerSearch: string;
  index: number;
  todayDate: Date = new Date();
  selectedCommercialParts: Array<any>;
  existingManufacturers: Array<any>;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  currentItemsToShow = [];
  x = 0;
  y = this.pageSize;
  currentPageIndex: number;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource(this.currentItemsToShow);

  constructor(private projectService: ApiService) {
  }


  ngOnInit() {
    this.getAllPartsOrders();
    this.getAllCommercialParts();
    this.getAllProjects();
    this.getAllSelected();
  }

  ngAfterViewInit() {
  }


  // initPaginator() {
  //   setTimeout(() => {
  //     this.currentItemsToShow = this.commercialParts;
  //   }, 200);
  // }


  initPaginator() {
    this.currentItemsToShow = this.commercialParts.slice(this.x, this.y);
  }

  onPageChange($event) {
    this.x = $event.pageIndex * $event.pageSize;
    this.y = $event.pageIndex * $event.pageSize + $event.pageSize;
    this.currentItemsToShow = this.commercialParts
      .slice($event.pageIndex * $event.pageSize, $event.pageIndex * $event.pageSize + $event.pageSize);
  }

  getAllSelected() {
    this.selectedCommercialParts = this.currentItemsToShow
      .filter(commercial => commercial.checked === true && commercial.invoiceId == null);
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
        this.index = this.partsOrders.length;
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
        this.initPaginator();
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
          this.reloadPartsAfterDeletion();
        },
        err => {
          alert('Failed to delete the part');
        }
      );
    }
  }

  deleteSelectedCommercialParts() {
    if (confirm('Do you want to delete selected parts?')) {
      this.projectService.deleteSelectedCommercialParts(this.currentItemsToShow
        .filter(commercial => commercial.checked === true).map(commercial => commercial.id)).subscribe(
        res => {
          console.log(res);
          this.reloadPartsAfterDeletion();
        },
        err => {
          alert('Failed to delete selected parts');
          console.log(err);
        }
      );
    }
  }

  createCommercialPart(partsOrderId: string) {
    const newCommercialPart: CommercialPart = {
      id: null,
      type: 'UNMARKED',
      orderSymbol: '',
      name: '',
      manufacturer: 'UNASSIGNED',
      quantity: '0',
      orderDate: this.todayDate,
      deliveryDate: null,
      price: '0',
      manufacturerId: '1',
      partsOrderId: partsOrderId,
      invoiceId: null,
    };

    this.projectService.saveCommercialPart(newCommercialPart).subscribe(
      res => {
        newCommercialPart.id = res.id;
        this.commercialParts.push(newCommercialPart);
        this.initPaginator();
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
        // this.currentItemsToShow = this.commercialParts.slice(0, 10);
        this.initPaginator();
      },
      err => {
        alert('An error has occurred while fetching the parts');
      }
    );
  }

  reloadPartsAfterDeletion() {
    this.projectService.getCommercialPartsByPartsOrder(this.selectedPartsOrder.id).subscribe(
      res => {
        this.commercialParts = res;
        this.currentItemsToShow = this.commercialParts.slice(this.x, this.y);
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
        this.currentItemsToShow = res;
        this.getAllSelected();
      }
    );
  }

  generateInvoice() {
    const newInvoice: Invoice = {
      id: null,
      commercialParts: this.currentItemsToShow.filter(commercial => commercial.checked === true).map(commercial => commercial.id)
    };

    if (confirm('You will create new invoice from selected parts, do you want to continue?')) {
      this.projectService.postInvoice(newInvoice).subscribe(
        res => {
          newInvoice.id = res.id;
          this.invoices.push(newInvoice);
          // this.updatecc();
          this.reloadPartsAfterDeletion();
        },
        err => {
          alert('An error has occurred while saving part');
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
        .filter(commercial => commercial.manufacturer === manufacturer && commercial.invoiceId == null)
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
          alert('An error has occurred while saving part');
          console.log('oops', err.err);
          console.log(JSON.stringify(err));
        }
      );
    }
  }


}
