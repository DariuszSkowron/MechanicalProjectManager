import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ProjectService {

  private baseUrl = 'http://localhost:8080/api/projects';

  constructor(private http: HttpClient) {
  }

  getProject(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);

  }

  createProject(project: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, project);
  }

  updateProject(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteProject(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  getProjectList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getProjectsByProjectNumber(projectNumber: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/number/${projectNumber}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, {responseType: 'text'});
  }


}
