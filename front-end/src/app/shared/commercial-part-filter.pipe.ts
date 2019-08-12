import { Pipe, PipeTransform } from '@angular/core';
import {CommercialPart} from '../commercial-parts/model/commercial-part';

@Pipe({
  name: 'commercialPartFilter'
})

export class CommercialPartFilterPipe implements PipeTransform {
  transform(commercialParts: CommercialPart[], nameOrOrderSymbolSearch?: string, manufacturerSearch?: string): CommercialPart[] {
    if (nameOrOrderSymbolSearch == null && (manufacturerSearch == null)) {
      return commercialParts;
    }

    if (nameOrOrderSymbolSearch == null) {
      return commercialParts = [...commercialParts.filter(n => n.manufacturer === manufacturerSearch)];
    }

    if (manufacturerSearch == null) {
      // tslint:disable-next-line:max-line-length
      return commercialParts = [...commercialParts.filter(n => n.name.includes(nameOrOrderSymbolSearch) || n.orderSymbol.includes(nameOrOrderSymbolSearch))];
    }

    // tslint:disable-next-line:max-line-length
    commercialParts = [...commercialParts.filter(n => (n.name.includes(nameOrOrderSymbolSearch) || n.orderSymbol.includes(nameOrOrderSymbolSearch)) && n.manufacturer === manufacturerSearch)];

    return commercialParts;
  }
}

