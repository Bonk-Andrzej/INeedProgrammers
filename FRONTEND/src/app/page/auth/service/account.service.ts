import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LoginForm} from "../dto/LoginForm";
import {Observable} from "rxjs";
import {environment} from "src/environments/environment";
import {JWToken} from "../dto/JWToken";
import {UserDto} from "../../panel/user/model/UserDto";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) {

  }

  url: string = environment.api.endpoint + '/api';

  authorizeUser(loginForm: LoginForm): Observable<JWToken> {
    return this.httpClient.post<JWToken>(this.url + '/authenticate/login-user', loginForm)
  }

  getCurrentLoginUser(): Observable<UserDto> {
    return this.httpClient.get<UserDto>(this.url + '/account')
  }


}
