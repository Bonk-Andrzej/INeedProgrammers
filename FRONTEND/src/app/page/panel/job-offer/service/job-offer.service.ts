import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JobOfferDto} from 'src/app/page/panel/job-offer/model/JobOfferDto';
import {environment} from 'src/environments/environment';
import {JobOfferForm} from 'src/app/page/panel/job-offer/model/JobOfferForm';

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  private url: string = environment.api.endpoint + '/job-offers';

  constructor(private httpClient: HttpClient) {
  }


  createJobOffer(jobOfferForm: JobOfferForm): Observable<JobOfferDto> {
    return this.httpClient.post<JobOfferDto>(this.url, jobOfferForm);
  }

  getJobOffer(id: number): Observable<JobOfferDto> {
    return this.httpClient.get<JobOfferDto>(this.url + `/${id}`);
  }

  getAllJobOffers(): Observable<JobOfferDto[]> {
    return this.httpClient.get<JobOfferDto[]>(this.url);
  }

  updateJobOffer(jobOfferForm: JobOfferForm, id: number): Observable<JobOfferDto> {
    return this.httpClient.put<JobOfferDto>((this.url + `/${id}`), jobOfferForm);
  }

  deleteJobOffer(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
