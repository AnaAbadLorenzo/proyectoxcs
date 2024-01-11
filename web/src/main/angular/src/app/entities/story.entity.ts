/**
* Represents a Story with essential details.
* @param id - The unique identifier for the Story.
* @param title - The title of the Story.
* @param gender - The gender associated with the story.
* @param topic1 - The primary topic or theme of the story.
* @param topic2 - An additional topic or theme related to the story.
* @param text - The content or text of the Story.
* @param author - The author of the Story.
* @param publicationDate - The date when the Story was published (can be null if not published yet).
*/
export class Story {
    constructor(
      public id: number,
      public title: string,
      public gender: string,
      public topic1: string,
      public topic2: string,
      public text: string,
      public author: string,
      public publicationDate: Date | null
    ) {
    }
  }
  