import { Component, OnInit } from '@angular/core';
import {Project} from '../project';
import {Observable} from 'rxjs';
import {ProjectService} from '../project.service';

@Component({
  selector: 'project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Observable<Project[]>;

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.reloadData();
  }

  deleteProjects() {
    this.projectService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('Error: ' + error));
  }

  reloadData() {
    this.projects = this.projectService.getProjectList();
  }

}
