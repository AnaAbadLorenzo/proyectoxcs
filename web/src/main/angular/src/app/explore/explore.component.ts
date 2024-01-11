import {Component , OnInit} from '@angular/core';
import {Story} from '../interfaces/story';
import {MicroStoryService} from '../services/microStory.service';
import {PageEvent} from '@angular/material/paginator';
import {ActivatedRoute} from '@angular/router';

interface Gender {
  value: string;
  viewValue: string;
}

interface Topic {
  value: string;
  viewValue: string;
}

interface PubDate {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'ms-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.scss']
})
export class ExploreComponent implements OnInit{
  public isLoading = false;
  public stories: Story[] = [];
  public totalItems: number = 10;
  public pageSize: number = 10;
  public pageSizeOptions: number[] = [5, 10, 25, 50, 100];
  public pageIndex: number = 0;
  public genderInputValue: string = '';
  public topicInputValue: string = '';
  public dateInputValue: string = '';

  constructor(
    private storyService: MicroStoryService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
      this.explore(this.genders[0].value, this.topics[0].value, this.pubdates[0].value);
  }

  explore(genderInputValue: string, topicInputValue: string, dateInputValue: string) {
    if (this.pageIndex == Infinity) this.pageIndex = 0;
    this.storyService.exploreStories(genderInputValue, topicInputValue, dateInputValue, this.formatDate(new Date()),
      this.pageIndex, this.pageSize)
      .subscribe(pagination => {
        this.stories = pagination.listaBusquedas;
        this.totalItems = pagination.tamanhoTotal;
        this.pageSize = pagination.numResultados;
        this.pageIndex = pagination.numResultados / pagination.inicio;
      })
      .add(() => this.isLoading = false);
  }

  onPageChange(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
    this.explore(this.genderInputValue, this.topicInputValue, this.dateInputValue);
  }

  genders: Gender[] = [
    {value: '', viewValue: 'Cualquier género'},
    {value: 'STORY', viewValue: 'Relato'},
    {value: 'NANO_STORY', viewValue: 'Microrrelato'},
    {value: 'POETRY', viewValue: 'Poesía'}
  ];

  topics: Topic[] = [
    {value: '', viewValue: 'Cualquier tema'},
    {value: 'ADVENTURE', viewValue: 'Aventura'},
    {value: 'SCIENCE', viewValue: 'Ciencia'},
    {value: 'FICTION', viewValue: 'Ficción'},
    {value: 'HISTORY', viewValue: 'Historia'},
    {value: 'CHILDREN', viewValue: 'Infantil'},
    {value: 'ROMANCE', viewValue: 'Romance'},
    {value: 'SUSPENSE', viewValue: 'Suspense'},
    {value: 'HORROR', viewValue: 'Horror'}
  ];

  pubdates: PubDate[] = [
    {value: this.formatDate(new Date(-8640000000000000)), viewValue: 'Cualquier momento'},
    {value: this.getStartDate(1), viewValue: 'Hoy'},
    {value: this.getStartDate(7), viewValue: 'Esta semana'},
    {value: this.getStartDate(30), viewValue: 'Este mes'},
    {value: this.getStartDate(365), viewValue: 'Este año'}
  ];

  getStartDate(daysOffset: number): string {
    const date = new Date();
    date.setDate(date.getDate() - daysOffset);

    const formattedDate = this.formatDate(date);
    return formattedDate;
  }

  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = this.padWithZero(date.getMonth() + 1);
    const day = this.padWithZero(date.getDate());
    const hours = this.padWithZero(date.getHours());
    const minutes = this.padWithZero(date.getMinutes());
    const seconds = this.padWithZero(date.getSeconds());

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  }

  private padWithZero(value: number): string {
    return value.toString().padStart(2, '0');
  }
}
