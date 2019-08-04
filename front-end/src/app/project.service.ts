import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MechanicalProcessing} from './processed-parts/model/mechanical-processing';
import {ProcessedPart} from './processed-parts/model/processed-part';
import {PartsOrder} from './commercial-parts/model/parts-order';
import {CommercialPart} from './commercial-parts/model/commercial-part';

@Injectable({
  providedIn: 'root'
})

export class ProjectService {

  private BASE_URL = 'http://localhost:8080/api';
  private PROJECTS_URL = `${this.BASE_URL}/projects`;
  private ALL_MECHANICAL_PROCESSING_URL = `${this.BASE_URL}/mechanicalProcessing/all`;
  private SAVE_UPDATE_MECHANICAL_PROCESSING = `${this.BASE_URL}/mechanicalProcessing`;
  private DELETE_MECHANICAL_PROCESSING_URL =  `${this.BASE_URL}/mechanicalProcessing/`;
  private ALL_PROCESSED_PARTS_URL = `${this.BASE_URL}/parts/all`;
  private PARTS_BY_MECHANICAL_PROCESSING = `${this.BASE_URL}/parts/byMechanicalProcessing/`;
  private SAVE_UPDATE_PROCESSED_PART_URL = `${this.BASE_URL}/parts`;
  private DELETE_PROCESSED_PART_URL = `${this.BASE_URL}/parts/`;
  private ALL_PARTS_ORDERS_URL = `${this.BASE_URL}/partsOrders/all`;
  private SAVE_UPDATE_PARTS_ORDERS_URL = `${this.BASE_URL}/partsOrders`;
  private DELETE_PARTS_ORDERS_URL = `${this.BASE_URL}/partsOrders/`;
  private ALL_COMMERCIAL_PARTS_URL = `${this.BASE_URL}/commercialParts/all`;
  private PARTS_BY_PARTS_ORDERS_URL = `${this.BASE_URL}/commercialParts/byPartsOrder/`;
  private SAVE_UPDATE_COMMERCIAL_PART_URL = `${this.BASE_URL}/commercialParts`;
  private DELETE_COMMERCIAL_PART_URL = `${this.BASE_URL}/commercialParts/`;




  constructor(private http: HttpClient) {
  }

  getProject(id: number): Observable<Object> {
    return this.http.get(`${this.PROJECTS_URL}/${id}`);

  }

  createProject(project: Object): Observable<Object> {
    return this.http.post(`${this.PROJECTS_URL}` + `/create`, project);
  }

  updateProject(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.PROJECTS_URL}/${id}`, value);
  }

  deleteProject(id: number): Observable<any> {
    return this.http.delete(`${this.PROJECTS_URL}/${id}`, {responseType: 'text'});
  }

  getProjectList(): Observable<any> {
    return this.http.get(`${this.PROJECTS_URL}`);
  }

  getProjectsByProjectNumber(projectNumber: number): Observable<any> {
    return this.http.get(`${this.PROJECTS_URL}/number/${projectNumber}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.PROJECTS_URL}` + `/delete`, {responseType: 'text'});
  }

  getAllMechanicalProcessing(): Observable<MechanicalProcessing[]> {
    return this.http.get<MechanicalProcessing[]>(this.ALL_MECHANICAL_PROCESSING_URL);
  }

  postMechanicalProcessing(mechanicalProcessing: MechanicalProcessing): Observable<MechanicalProcessing> {
    return this.http.post<MechanicalProcessing>(this.SAVE_UPDATE_MECHANICAL_PROCESSING, mechanicalProcessing);
  }

  deleteMechanicalProcessing(id: string): Observable<any> {
    return this.http.delete(this.DELETE_MECHANICAL_PROCESSING_URL + id);
  }

  getAllProcessedParts(): Observable<ProcessedPart[]> {
    return this.http.get<ProcessedPart[]>(this.ALL_PROCESSED_PARTS_URL);
  }

  getProcessedPartsByMechanicalProcessing(mechanicalProcessingId: string): Observable<ProcessedPart[]> {
    return this.http.get<ProcessedPart[]>(this.PARTS_BY_MECHANICAL_PROCESSING + mechanicalProcessingId);
  }

  saveProcessedPart(processedPart: ProcessedPart): Observable<ProcessedPart> {
    return this.http.post<ProcessedPart>(this.SAVE_UPDATE_PROCESSED_PART_URL, processedPart);
  }

  deleteProcessedPart(processedPartId: string): Observable<any> {
    return this.http.delete(this.DELETE_PROCESSED_PART_URL + processedPartId);
  }



  getAllPartsOrders(): Observable<PartsOrder[]> {
    return this.http.get<PartsOrder[]>(this.ALL_PARTS_ORDERS_URL);
  }

  postPartsOrder(partsOrder: PartsOrder): Observable<PartsOrder> {
    return this.http.post<PartsOrder>(this.SAVE_UPDATE_PARTS_ORDERS_URL, partsOrder);
  }

  deletePartsOrder(id: string): Observable<any> {
    return this.http.delete(this.DELETE_PARTS_ORDERS_URL + id);
  }

  getAllCommercialParts(): Observable<CommercialPart[]> {
    return this.http.get<CommercialPart[]>(this.ALL_COMMERCIAL_PARTS_URL);
  }

  getCommercialPartsByPartsOrder(partsOrderId: string): Observable<CommercialPart[]> {
    return this.http.get<CommercialPart[]>(this.PARTS_BY_PARTS_ORDERS_URL + partsOrderId);
  }

  saveCommercialPart(commercialPart: CommercialPart): Observable<CommercialPart> {
    return this.http.post<CommercialPart>(this.SAVE_UPDATE_COMMERCIAL_PART_URL, commercialPart);
  }

  deleteCommercialPart(commercialPartId: string): Observable<any> {
    return this.http.delete(this.DELETE_COMMERCIAL_PART_URL + commercialPartId);
  }



}
