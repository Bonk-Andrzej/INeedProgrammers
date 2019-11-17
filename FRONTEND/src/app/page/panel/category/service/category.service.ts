import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {CategoryForm} from 'src/app/page/panel/category/model/CategoryForm';
import {CategoryDto} from 'src/app/page/panel/category/model/CategoryDto';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url: string = environment.api.endpoint + '/categories';

  constructor(private httpClient: HttpClient) {
  }


  createCategory(categoryForm: CategoryForm): Observable<CategoryDto> {
    return this.httpClient.post<CategoryDto>(this.url, categoryForm);
  }

  getCategory(id: number): Observable<CategoryDto> {
    return this.httpClient.get<CategoryDto>(this.url + `/${id}`);
  }

  getAllCategories(): Observable<CategoryDto[]> {
    return this.httpClient.get<CategoryDto[]>(this.url);
  }

  updateCategory(categoryForm: CategoryForm, id: number): Observable<CategoryDto> {
    return this.httpClient.put<CategoryDto>(this.url + `/${id}`, categoryForm);
  }

  deleteCategory(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
