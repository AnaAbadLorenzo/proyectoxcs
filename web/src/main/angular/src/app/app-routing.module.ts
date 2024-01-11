import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { StoryReaderComponent } from './shared/story-reader/story-reader.component';
import { SearchComponent } from "./search/search.component";
import {ExploreComponent} from "./explore/explore.component";
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'shared/story-reader/:id', component: StoryReaderComponent },
  { path: 'search/:query', component: SearchComponent },
  { path: 'explore', component: ExploreComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
