import { Component, OnInit } from '@angular/core';
import { Story } from '../interfaces/story';
import { MicroStoryService } from '../services/microStory.service';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'ms-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  public isLoading = false;
  public stories: Story[] = [];
  public totalItems: number = 10;
  public pageSize: number = 10;
  public pageSizeOptions: number[] = [5, 10, 25, 50, 100];
  public pageIndex: number = 0;
  public searchInputValue: string = '';

  constructor(
    private storyService: MicroStoryService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const query = params['query'];
      this.searchInputValue = query;
      this.search(query);
    });
  }

  search(searchInputValue: string) {
    if (this.pageIndex == Infinity) this.pageIndex = 0;
    this.storyService.getByText(searchInputValue, this.pageIndex, this.pageSize)
      .subscribe(pagination => {
        this.stories = pagination.listaBusquedas;
        this.totalItems = pagination.tamanhoTotal;
        this.pageSize = pagination.numResultados;
        this.pageIndex = pagination.numResultados / pagination.inicio;
      })
      .add(() => this.isLoading = false)
  }

  onPageChange(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
    this.search(this.searchInputValue);
  }
}
