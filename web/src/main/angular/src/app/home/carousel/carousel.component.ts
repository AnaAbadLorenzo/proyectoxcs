import {Component} from '@angular/core';
import {MicroStoryService} from "../../services/microStory.service";
import {Story} from "../../interfaces/story";

@Component({
  selector: 'ms-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent {
  public carouselItems: { gender: string, stories: Story[] }[] = [];
  public currentSlide = 0;

  constructor(private storyService: MicroStoryService) {
  }

  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.carouselItems.length;
  }

  previousSlide() {
    this.currentSlide = (this.currentSlide - 1 + this.carouselItems.length) % this.carouselItems.length;
  }

  ngOnInit(): void {
    this.getMostViewed();
    setInterval(() => {
      this.nextSlide();
    }, 10_000);
  }

  getMostViewed(): void {
    this.storyService.getMostViewed()
      .subscribe(stories => {
        stories.forEach(story => {
          this.fillCarouselItemsByGender(story);
        });
      });
  }

  fillCarouselItemsByGender(story: Story) {
    const existsGenderInCarousel = this.carouselItems.find(item => item.gender === story.gender);
    if (existsGenderInCarousel) {
      existsGenderInCarousel.stories.push(story);
    } else {
      this.carouselItems.push({gender: story.gender, stories: [story]})
    }
  }
}
