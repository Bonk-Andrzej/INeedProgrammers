import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {CategoryDto} from 'src/app/page/panel/category/model/CategoryDto';
import {LocationForm} from 'src/app/page/panel/location/model/LocationForm';
import {LocationDto} from 'src/app/page/panel/location/model/LocationDto';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private url: string = environment.api.endpoint + '/locations';

  constructor(private httpClient: HttpClient) {
  }


  createLocation(locationForm: LocationForm): Observable<LocationDto> {
    return this.httpClient.post<CategoryDto>(this.url, locationForm);
  }

  getLocation(id: number): Observable<LocationDto> {
    return this.httpClient.get<LocationDto>(this.url + `/${id}`);
  }

  getAllLocations(): Observable<LocationDto[]> {
    return this.httpClient.get<LocationDto[]>(this.url);
  }

  updateLocation(locationForm: LocationForm, id: number): Observable<LocationDto> {
    return this.httpClient.put<LocationDto>(this.url + `/${id}`, locationForm);
  }

  deleteLocation(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
