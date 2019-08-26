import { Pipe, PipeTransform } from '@angular/core';
import {CommercialPart} from '../commercial-parts/model/commercial-part';
import * as _ from 'lodash';

@Pipe({
  name: 'duplicateFilter',
  pure: false
})
export class DuplicateFilterPipe implements PipeTransform {
    transform(value: any): any {
      if (value !== undefined && value !== null) {
        return _.uniqBy(value, 'manufacturer');
      }
      return value;
    }
}
