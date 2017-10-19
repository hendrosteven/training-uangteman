import {Injectable} from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx'
import {Product} from '../classes/product.class';
import {SearchNameForm} from '../classes/searchform.class';

@Injectable()
export class ProductService {

    url : string = 'http://localhost:9191/product';
    headers : any;
    options : any;

    constructor(private http : Http) {
        this.headers = new Headers({
            'Content-Type': 'application/json', 
            'Cache-Control': 'no-cache',
            'Authorization': 'Basic '+ localStorage.getItem('token')
        });
        this.options = new RequestOptions({headers: this.headers});
    }

    getAllProduct() {
        return this
            .http
            .get(this.url, this.options)
            .map(res => res.json())
            .catch(this.handleError)
    }

    saveProduct(product : Product) {
        return this
            .http
            .post(this.url, product, this.options)
            .map(res => res.json())
            .catch(this.handleError)
    }

    removeProduct(id) {
        return this
            .http
            .delete(this.url+'/'+id, this.options)
            .map(res => res.json())
            .catch(this.handleError)
    }

    searchProduct(searchName : SearchNameForm) {
        return this
            .http
            .post(this.url + '/name', searchName, this.options)
            .map(res => res.json())
            .catch(this.handleError)
    }

    handleError(error) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}