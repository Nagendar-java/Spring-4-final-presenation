import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';
import { Book } from './book';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class DigitalService {

  constructor(private _http:HttpClient) { }

  private baseURL="http://Userservice-env-1.eba-mn3zqudu.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/signin";
  private baseURLForSignup="http://Userservice-env-1.eba-mn3zqudu.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/signup";

  loginUser(user: User):Observable<object>{
console.log(user);
return this._http.post(`${this.baseURL}`, user);

  }

  addUser(user: User):Observable<object>{
    console.log(user);
    return this._http.post(`${this.baseURLForSignup}`, user);
    }
    allBookDetails():Observable<any>{
      return this._http.get<any>('http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/all');
    }
  
    viewBook(id:number):Observable<any>{
      return this._http.get<any>('http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/book/'+id);
    }
  
    editBook(id:number,b:Book):Observable<any>{
      return this._http.put<any>('http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/book/'+id,b);
    }
  
    createBook(id:number,b:Book):Observable<any>{
      return this._http.post<any>('http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/author/'+id+'/books',b);
    }
}
