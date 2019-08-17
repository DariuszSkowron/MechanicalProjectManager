import {Component, OnInit} from '@angular/core';
import {Project} from '../project';
import {ApiService} from '../../shared/api.service';

@Component({
  selector: 'search-project',
  templateUrl: './search-project.component.html',
  styleUrls: ['./search-project.component.css']
})
export class SearchProjectComponent implements OnInit {

  projectNumber: number;
  projects: Project[];

  constructor(private dataService: ApiService) {
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
          alert('Cant find any projects');
        }
      );
  }

  onSubmit() {
    this.getProjectsByProjectNumber();
  }

}
