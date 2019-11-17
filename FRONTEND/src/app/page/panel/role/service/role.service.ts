import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {RoleDto} from 'src/app/page/panel/role/model/RoleDto';
import {RoleForm} from 'src/app/page/panel/role/model/RoleForm';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private url: string = environment.api.endpoint + '/roles';

  constructor(private httpClient: HttpClient) {
  }


  createRole(roleForm: RoleForm): Observable<RoleDto> {
    return this.httpClient.post<RoleDto>(this.url, roleForm);
  }

  getRole(id: number): Observable<RoleDto> {
    return this.httpClient.get<RoleDto>(this.url + `/${id}`);
  }

  getAllRoles(): Observable<RoleDto[]> {
    return this.httpClient.get<RoleDto[]>(this.url);
  }

  updateRole(roleForm: RoleForm, id: number): Observable<RoleDto> {
    return this.httpClient.put<RoleDto>(this.url + `/${id}`, roleForm);
  }

  deleteRole(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }
}
