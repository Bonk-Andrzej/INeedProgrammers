import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {TechnologyDto} from 'src/app/page/panel/technology/model/TechnologyDto';
import {TechnologyForm} from 'src/app/page/panel/technology/model/TechnologyForm';

@Injectable({
  providedIn: 'root'
})
export class TechnologyService {

  private url: string = environment.api.endpoint + '/locations';

  constructor(private httpClient: HttpClient) {
  }

  createTechnology(technologyForm: TechnologyForm): Observable<TechnologyDto> {
    return this.httpClient.post<TechnologyDto>(this.url, technologyForm);
  }

  getTechnology(id: number): Observable<TechnologyDto> {
    return this.httpClient.get<TechnologyDto>(this.url + `/${id}`);
  }

  getAllTechnologies(): Observable<TechnologyDto[]> {
    return this.httpClient.get<TechnologyDto[]>(this.url);
  }

  updateTechnology(technologyForm: TechnologyForm, id: number): Observable<TechnologyDto> {
    return this.httpClient.put<TechnologyDto>(this.url + `/${id}`, technologyForm);
  }

  deleteTechnology(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
