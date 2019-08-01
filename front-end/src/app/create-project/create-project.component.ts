import {Component, OnInit} from '@angular/core';
import {Project} from '../project';
import {ProjectService} from '../project.service';

@Component({
  selector: 'create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  project: Project = new Project();
  submitted = false;

  constructor(private projectService: ProjectService) {
  }

  ngOnInit() {
  }

  newProject(): void {
    this.submitted = false;
    this.project = new Project();
  }

  save() {
    this.projectService.createProject(this.project)
      .subscribe(data => console.log(data), error => console.log(error));
    this.project = new Project();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
