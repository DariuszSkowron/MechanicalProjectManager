import {Pipe, PipeTransform} from '@angular/core';
import {ProcessedPart} from "../processed-parts/model/processed-part";

@Pipe({
  name: 'ProcessedPartDwgNumberFilter'
})
export class ProcessedPartDwgNumberFilterPipe implements PipeTransform {

  transform(processedParts: ProcessedPart[], mainProcessSearch?: string, nameSearch?: string): ProcessedPart[] {
    if (nameSearch == null && (mainProcessSearch == null)) {
      return processedParts;
    }

    if (nameSearch == null) {
      return processedParts = [...processedParts.filter(n => n.mainProcess === mainProcessSearch)];
    }

    if (mainProcessSearch == null){
      return processedParts = [...processedParts.filter(n => n.drawingNumber.includes(nameSearch))];

    }

    processedParts = [...processedParts.filter(n => n.drawingNumber.includes(nameSearch) && n.mainProcess === mainProcessSearch)]

    return processedParts;
  }
}

