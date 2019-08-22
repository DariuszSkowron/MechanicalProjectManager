import { Component, OnInit } from '@angular/core';
import {Manufacturer} from './model/manufacturer';
import {SalesRepresentative} from './model/sales-representative';
import {ApiService} from '../shared/api.service';
import {MechanicalProcessing} from '../processed-parts/model/mechanical-processing';
import {ProcessedPart} from '../processed-parts/model/processed-part';

@Component({
  selector: 'manufacturers',
  templateUrl: './manufacturers.component.html',
  styleUrls: ['./manufacturers.component.scss']
})
export class ManufacturersComponent implements OnInit {
  manufacturers: Manufacturer[] = [];
  salesRepresentatives: SalesRepresentative[] = [];
  selectedManufacturer: Manufacturer;
  nameSearch: string;
  manufacturerSearch: string;

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getAllManufacturers();
    this.getAllSalesRepresentatives();
  }

  filterSalesRepresentativesByManufacturer(manufacturer: any) {
    this.salesRepresentatives.find(rep => rep.manufacturerId === manufacturer);
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


  getAllSalesRepresentatives() {
    this.apiService.getAllSalesRepresentatives().subscribe(
      res => {
        console.log(res);
      },
      err => {
        alert('While downloading sales representatives occurred an error');
      }
    );
  }

  createManufacturer() {
    const newManufacturer: Manufacturer = {
      name: 'New Manufacturer',
      id: null,
      numberOfSalesRepresentatives: 0
    };

    this.apiService.postManufacturer(newManufacturer).subscribe(
      res => {
        newManufacturer.id = res.id;
        this.manufacturers.push(newManufacturer);
      },
      err => {
        alert('An error has occurred while trying to save new manufacturer');
      }
    );
  }

  updateManufacturer(updateManufacturer: Manufacturer) {
    this.apiService.postManufacturer(updateManufacturer).subscribe(
      res => {

      },
      err => {
        alert('An error has occured while updating Manufacturer');
      }
    );
  }

  deleteManufacturer(manufacturer: Manufacturer) {
    if (confirm('You will now delete manufacturer, do you want to proceed?')) {
      this.apiService.deleteManufacturer(manufacturer.id).subscribe(
        res => {
          const indexOfManufacturer = this.manufacturers.indexOf(manufacturer);
          this.manufacturers.splice(indexOfManufacturer, 1);
        },
        err => {
          alert('Failed to delete manufacturer');
        }
      );
    }
  }

  deleteSalesRepresentative(salesRepresentative: SalesRepresentative) {
    if (confirm('You will delete this Sales Representative, do you want to proceed?')) {
      this.apiService.deleteSalesRepresentative(salesRepresentative.id).subscribe(
        res => {
          const indexOfSalesRepresentative = this.salesRepresentatives.indexOf(salesRepresentative);
          this.salesRepresentatives.splice(indexOfSalesRepresentative, 1);
        },
        err => {
          alert('Failed to delete Sales Representative');
        }
      );
    }
  }

  createSalesRepresentative(manufacturerId: string) {
    const newSalesRepresentative: SalesRepresentative = {
      id: null,
      name: '',
      lastName: '',
      email: '',
      manufacturerId: manufacturerId
    };

    this.apiService.saveSalesRepresentative(newSalesRepresentative).subscribe(
      res => {
        newSalesRepresentative.id = res.id;
        this.salesRepresentatives.push(newSalesRepresentative);
      },
      err => {
        alert('An error has occurred while adding Sales Representative');
      }
    );
  }

  selectManufacturer(manufacturer: Manufacturer) {
    this.selectedManufacturer = manufacturer;
    this.apiService.getSalesRepresentativeByManufacturer(manufacturer.id).subscribe(
      res => {
        this.salesRepresentatives = res;
      },
      err => {
        alert('An error has occurred while fetching sales representative');
      }
    );
  }

  updateSalesRepresentative(updateSalesRepresentative: SalesRepresentative) {
    this.apiService.saveSalesRepresentative(updateSalesRepresentative).subscribe(
      res => {
      },
      err => {
        alert('An error occurred while updating sales representative' + JSON.stringify(err));
      }
    );
  }

  selectAllSalesRepresentatives() {
    this.selectedManufacturer = null;
    this.getAllSalesRepresentatives();
  }

}
