import {Component, Input, OnInit} from '@angular/core';
import {ApiService} from '../../shared/api.service';
import {Project} from '../project';
import {ProjectListComponent} from '../project-list/project-list.component';

@Component({
  selector: 'project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  @Input() project: Project;


  constructor(private projectService: ApiService, private listComponent: ProjectListComponent) {
  }

  ngOnInit() {
  }

  deleteProject() {
    this.projectService.deleteProject(this.project.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }

}
