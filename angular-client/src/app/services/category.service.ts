import {Injectable} from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx'
import { Category } from '../classes/category.class';


@Injectable()
export class CategoryService{

    url: string = 'http://localhost:9191/category';
    headers: any;
    options: any;

    constructor(private http : Http) {        
        console.log(localStorage.getItem('token'));
        this.headers = new Headers({
                    'Content-Type': 'application/json', 
                    'Cache-Control': 'no-cache',
                    'Authorization': 'Basic '+ localStorage.getItem('token')
                });
        this.options = new RequestOptions({headers: this.headers});        
    }

    getAllCategory() {     
        return this
            .http
            .get(this.url, this.options)
            .map(res => res.json())
            .catch(this.handleError)
    }

    saveCategory(category: Category){
        return this
        .http
        .post(this.url,category,this.options)
        .map(res => res.json())
        .catch(this.handleError)
    }

    handleError(error) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}