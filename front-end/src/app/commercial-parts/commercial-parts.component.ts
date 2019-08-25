import {Component, OnInit} from '@angular/core';
import {PartsOrder} from './model/parts-order';
import {CommercialPart} from './model/commercial-part';
import {ApiService} from '../shared/api.service';


@Component({
  selector: 'commercial-parts',
  templateUrl: './commercial-parts.component.html',
  styleUrls: ['./commercial-parts.component.scss']
})
export class CommercialPartsComponent implements OnInit {
  partsOrders: PartsOrder[] = [];
  commercialParts: CommercialPart[] = [];
  selectedPartsOrder: PartsOrder;
  nameOrOrderSymbolSearch: string;
  manufacturerSearch: string;

  constructor(private projectService: ApiService) {
  }

  ngOnInit() {
    this.getAllPartsOrders();
    this.getAllCommercialParts();
  }

  filterPartsByManufacturer(manufacturer: any) {
    this.commercialParts.find(part => part.manufacturer === manufacturer);
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
      name: 'Parts order number #',
      id: null,
      numberOfParts: 0
    };

    this.projectService.postPartsOrder(newPartsOrder).subscribe(
      res => {
        newPartsOrder.id = res.id;
        this.partsOrders.push(newPartsOrder);
      },
      err => {
        alert('An error has occurred while trying to save the parts order list');
      }
    );
  }

  updatePartsOrder(updatePartsOrder: PartsOrder) {
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
      manufacturer: ``,
      quantity: '',
      orderDate: '',
      deliveryDate: '',
      price: '',
      manufacturerId: '',
      partsOrderId: partsOrderId,
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
      },
      err => {
        alert('An error occurred while updating the part' + JSON.stringify(err));
      }
    );
  }

  selectAllProcessedParts() {
    this.selectedPartsOrder = null;
    this.getAllCommercialParts();
  }


}
