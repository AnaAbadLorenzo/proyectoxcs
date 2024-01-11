import {Injectable} from '@angular/core';
import {Story} from '../interfaces/story';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of, catchError, tap, map} from 'rxjs';
import {Pagination} from "../interfaces/pagination";

@Injectable({
  providedIn: 'root'
})
export class MicroStoryService {

  private recent = `${environment.api}/microstory/recent`
  private urlMicroStoryById = `${environment.api}/microstory`
  private urlSearchStoryByText = `${environment.api}/microstory`
  private urlGetMostViewed = `${environment.api}/microstory/hottest`
  private urlExploreStory = `${environment.api}/microstory`

  constructor(private http: HttpClient) {
  }

  getRecent(): Observable<Story[]> {

    return this.http.get<Story[]>(this.recent)
      .pipe(
        tap(stories => stories.forEach(story => story.publicationDate = story.publicationDate && new Date(story.publicationDate)))
      );
  }

  getById(id: number): Observable<Story> {
    const url = `${this.urlMicroStoryById}/${id}`;
    return this.http.get<Story>(url).pipe(
      tap(_ => _.publicationDate = _.publicationDate && new Date(_.publicationDate))
    );
  }

  getByText(text: String, page: number, maxResults: number): Observable<Pagination<Story>> {
    const url = `${this.urlSearchStoryByText}?contains=${text}&page=${page}&maxResults=${maxResults}`;
    return this.http.get<Pagination<Story>>(url).pipe(
      tap(pagination => pagination.listaBusquedas.forEach(story => story.publicationDate = story.publicationDate && new Date(story.publicationDate)))
    );
  }

  exploreStories(gender: String, topic: String, startDate: String, endDate: String, page:number, maxResults:number): Observable<Pagination<Story>> {
    const url = `${this.urlExploreStory}?gender=${gender}&topic=${topic}&startDate=${startDate}&endDate=${endDate}&page=${page}&maxResults=${maxResults}`;
    return this.http.get<Pagination<Story>>(url).pipe(
      tap(pagination => pagination.listaBusquedas.forEach(story => story.publicationDate = story.publicationDate && new Date(story.publicationDate)))
    );
  }

  getMostViewed(): Observable<Story[]> {
    return this.http.get<Story[]>(this.urlGetMostViewed).pipe(
      tap(stories => stories.forEach(story => story.publicationDate = story.publicationDate && new Date(story.publicationDate)))
    );
  }


}
