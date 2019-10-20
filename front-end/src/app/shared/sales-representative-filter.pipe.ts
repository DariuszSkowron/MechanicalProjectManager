import { Pipe, PipeTransform } from '@angular/core';
import {SalesRepresentative} from '../manufacturers/model/sales-representative';

@Pipe({
  name: 'salesRepresentativeFilter'
})
export class SalesRepresentativeFilterPipe implements PipeTransform {

  transform(salesRepresentatives: SalesRepresentative[], manufacturerSearch?: string, nameSearch?: string): SalesRepresentative[] {
    if (nameSearch == null && (manufacturerSearch == null)) {
      return salesRepresentatives;
    }

    if (nameSearch == null) {
      return salesRepresentatives = [...salesRepresentatives.filter(n => n.manufacturerId === manufacturerSearch)];
    }

    if (manufacturerSearch == null) {
      return salesRepresentatives = [...salesRepresentatives.filter(n => n.lastName.includes(nameSearch))];

    }

    salesRepresentatives = [...salesRepresentatives
      .filter(n => n.lastName.includes(nameSearch) && n.manufacturerId === manufacturerSearch)];

    return salesRepresentatives;
  }
}
