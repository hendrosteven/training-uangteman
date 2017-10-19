import {Injectable} from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx'
import { Users } from '../classes/users.class';
import {Md5} from 'ts-md5/dist/md5';
import { LoginForm } from '../classes/loginform.class';


@Injectable()
export class UserService{

    url: string = 'http://localhost:9191/users';
    headers: any;
    options: any;

    constructor(private http : Http) {
        this.headers = new Headers({'Content-Type': 'application/json', 
                'Cache-Control': 'no-cache'});
        this.options = new RequestOptions({headers: this.headers});        
    }

    register(users: Users) {   
        let tmpUser =  Object.assign({}, users);
        tmpUser.password = Md5.hashStr(tmpUser.password).toString();
        return this
            .http
            .post(this.url+'/register', tmpUser, this.options)
            .map(res => res.json())
            .catch(this.handleError)
    }

    login(loginForm: LoginForm){        
        let tmpForm =  Object.assign({}, loginForm);
        tmpForm.password = Md5.hashStr(tmpForm.password).toString();
        return this
        .http
        .post(this.url+'/login', tmpForm, this.options)
        .map(res => res.json())
        .catch(this.handleError)
    }

    handleError(error) {       
        return Observable.throw(error.json() || 'Server error');
    }

}