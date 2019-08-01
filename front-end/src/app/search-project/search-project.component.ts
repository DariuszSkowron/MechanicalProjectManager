import {Component, OnInit} from '@angular/core';
import {Project} from '../project';
import {ProjectService} from '../project.service';

@Component({
  selector: 'search-project',
  templateUrl: './search-project.component.html',
  styleUrls: ['./search-project.component.css']
})
export class SearchProjectComponent implements OnInit {

  projectNumber: number;
  projects: Project[];

  constructor(private dataService: ProjectService) {
  }

  ngOnInit() {
    this.projectNumber = 0;
  }

  private getProjectsByProjectNumber() {
    this.dataService.getProjectsByProjectNumber(this.projectNumber)
      .subscribe(projects => {
          this.projects = projects;
        },
        err => {
          alert('An error has occured while downloading the notes;');
        }
      );
  }

  onSubmit() {
    this.getProjectsByProjectNumber();
  }

}
