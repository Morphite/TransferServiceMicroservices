import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AuthService {

  private urlLogin = '/auth/login/';
  private urlSignUp = '/auth/sign-up/';

  constructor(private httpClient: HttpClient) {
  }

}
