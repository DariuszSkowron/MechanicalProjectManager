import {Component, Input, OnInit} from '@angular/core';
import {ProjectService} from '../project.service';
import {Project} from '../project';
import {ProjectListComponent} from '../project-list/project-list.component';

@Component({
  selector: 'project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  @Input() project: Project;


  constructor(private projectService: ProjectService, private listComponent: ProjectListComponent) {
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
