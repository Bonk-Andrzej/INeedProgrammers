import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {CategoryDto} from 'src/app/page/panel/category/model/CategoryDto';
import {SeniorityDto} from 'src/app/page/panel/seniority/model/SeniorityDto';
import {SeniorityForm} from 'src/app/page/panel/seniority/model/SeniorityForm';

@Injectable({
  providedIn: 'root'
})
export class SeniorityService {

  private url: string = environment.api.endpoint + '/seniorities';

  constructor(private httpClient: HttpClient) {
  }


  createSeniority(seniorityForm: SeniorityForm): Observable<SeniorityDto> {
    return this.httpClient.post<CategoryDto>(this.url, seniorityForm);
  }

  getSeniority(id: number): Observable<SeniorityDto> {
    return this.httpClient.get<SeniorityDto>(this.url + `/${id}`);
  }

  getAllSeniorities(): Observable<SeniorityDto[]> {
    return this.httpClient.get<SeniorityDto[]>(this.url);
  }

  updateSeniority(seniorityForm: SeniorityForm, id: number): Observable<SeniorityDto> {
    return this.httpClient.put<SeniorityDto>(this.url + `/${id}`, seniorityForm);
  }

  deleteSeniority(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
