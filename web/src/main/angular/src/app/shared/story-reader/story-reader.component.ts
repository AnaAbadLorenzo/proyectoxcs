import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MicroStoryService } from '../../services/microStory.service'
import { Story } from '../../entities/story.entity'


@Component({
  selector: 'ms-story-reader',
  templateUrl: './story-reader.component.html',
  styleUrls: ['./story-reader.component.scss']
})
export class StoryReaderComponent {
  isLoading = true;
  public story: Story = new Story(0,"","","","","","",null);
  constructor(
    private route: ActivatedRoute,
    private microStoryService: MicroStoryService) {
  }

  ngOnInit(): void {
    this.getStoryById();
  }
  
  /**
   * Fetches a story by its ID from the StoryService.
   * 
   *@returns the story entity.
   */
  public getStoryById(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.microStoryService.getById(id)
      .subscribe(
        story => {
          console.log('story:', story);
          this.story = story;
        },
        error => {
        
        }
      );
  }

  goBack(): void {
    window.history.back();
  }
  
}
