export interface Story {
    id: number;
    title: string;
    text: string;
    gender: string;
    topic1: string;
    topic2: string;
    author: string;
    publicationDate: Date | null;
}