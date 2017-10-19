import { Component, OnInit } from '@angular/core';
import { Product } from '../classes/product.class';
import { ProductService } from '../services/product.service';
import { CategoryService } from '../services/category.service';
import { Category } from '../classes/category.class';
import { SearchNameForm } from '../classes/searchform.class';
import {NgProgressService} from 'ngx-progressbar';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[] = [];
  product: Product = new Product();
  categories: Category[] = [];
  searchName: SearchNameForm = new SearchNameForm();

  constructor(private progressService: NgProgressService,private productService: ProductService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.loadCategory();
    this.loadProduct();
  }

  loadCategory(){
    this.progressService.start();
    this.categoryService.getAllCategory().subscribe(output=>{
      this.progressService.done();
      this.categories = output;
    },error=>{
      this.progressService.done();
    });
  }

  loadProduct(){
    this.progressService.start();
    this.productService.getAllProduct().subscribe(output=>{
      this.progressService.done();
      this.products = output;
    },error=>{
      this.progressService.done();
    });
  }

  onProductSave(){
    this.progressService.start();
    this.productService.saveProduct(this.product).subscribe(output=>{
      this.progressService.done();
      this.products.push(output);
      this.product = new Product();
    }, error=>{
      this.progressService.done();
    })
  }

  onSearchProduct(){
    this.progressService.start();
    this.products = [];
    this.productService.searchProduct(this.searchName).subscribe(output=>{
      this.progressService.done();
      this.products = output;
    },error=>{
      this.progressService.done();
    });
  }

  onDeleteProduct(id){   
    this.progressService.start();
    this.productService.removeProduct(id).subscribe(output=>{
      this.progressService.done();
      this.loadProduct();
    },error=>{
      this.progressService.done();
    });
  }

}
