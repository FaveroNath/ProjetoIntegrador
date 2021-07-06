import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLogin } from '../model/Userlogin';

import { Usuarios } from '../model/Usuarios';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.post<UserLogin>('https://appcafunematerno.herokuapp.com/usuarios/logar', userLogin)
  }

  cadastrar(user: Usuarios): Observable<Usuarios>{
    return this.http.post<Usuarios>('https://appcafunematerno.herokuapp.com/usuarios/cadastrar', user)
  }
}
