import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {UserForm} from 'src/app/page/panel/user/model/UserForm';
import {UserDto} from 'src/app/page/panel/user/model/UserDto';
import {RoleForm} from 'src/app/page/panel/role/model/RoleForm';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = environment.api.endpoint + '/users';

  constructor(private httpClient: HttpClient) {
  }


  createUser(userForm: UserForm): Observable<UserDto> {
    return this.httpClient.post<UserDto>(this.url, userForm);
  }

  getUser(id: number): Observable<UserDto> {
    return this.httpClient.get<UserDto>(this.url + `/${id}`);
  }

  getAllUsers(): Observable<UserDto[]> {
    return this.httpClient.get<UserDto[]>(this.url);
  }

  updateUser(userForm: UserForm, id: number): Observable<UserDto> {
    return this.httpClient.put<UserDto>(this.url + `/${id}`, userForm);
  }

  deleteUser(id: number, version: number) {
    return this.httpClient.delete(`/${id}?version=${version}`);
  }

  changeUserRole(id: number, roleForm: RoleForm) {
    this.httpClient.patch(this.url + `/${id}`, roleForm);
  }

}
