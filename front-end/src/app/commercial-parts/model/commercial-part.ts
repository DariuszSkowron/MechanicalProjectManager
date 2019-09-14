export class CommercialPart {
  id: string;
  type: string;
  orderSymbol: string;
  name: string;
  manufacturer: string;
  quantity: string;
  orderDate: Date;
  deliveryDate: Date;
  price: string;
  partsOrderId: string;
  manufacturerId: string;
  checked?: boolean;
}
