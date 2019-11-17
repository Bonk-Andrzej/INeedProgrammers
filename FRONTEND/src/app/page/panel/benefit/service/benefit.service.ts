import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {CategoryDto} from 'src/app/page/panel/category/model/CategoryDto';
import {BenefitForm} from 'src/app/page/panel/benefit/model/BenefitForm';
import {BenefitDto} from 'src/app/page/panel/benefit/model/BenefitDto';

@Injectable({
  providedIn: 'root'
})
export class BenefitService {

  private url: string = environment.api.endpoint + '/benefits';

  constructor(private httpClient: HttpClient) {
  }


  createBenefit(benefitForm: BenefitForm): Observable<BenefitDto> {
    return this.httpClient.post<CategoryDto>(this.url, benefitForm);
  }

  getBenefit(id: number): Observable<BenefitDto> {
    return this.httpClient.get<BenefitDto>(this.url + `/${id}`);
  }

  getAllBenefits(): Observable<BenefitDto[]> {
    return this.httpClient.get<BenefitDto[]>(this.url);
  }

  updateBenefit(benefitForm: BenefitForm, id: number): Observable<BenefitDto> {
    return this.httpClient.put<CategoryDto>(this.url + `/${id}`, benefitForm);
  }

  deleteBenefit(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
