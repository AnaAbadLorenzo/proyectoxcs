import { Component } from '@angular/core';
import { MicroStoryService } from '../services/microStory.service'
import { Story } from '../interfaces/story'

@Component({
  selector: 'ms-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent {


  isLoading = true;
  stories: Story[] = [];
  constructor(private storyService: MicroStoryService) {}

  ngOnInit(): void {
    this.getRecent();
  }
  
  getRecent(): void {
    this.storyService.getRecent()
      .subscribe(stories => this.stories = stories)
      .add(() => this.isLoading = false)
  }
}
