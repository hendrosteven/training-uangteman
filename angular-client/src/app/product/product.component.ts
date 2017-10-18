import { Component, OnInit } from '@angular/core';
import { Product } from '../classes/product.class';
import { ProductService } from '../services/product.service';
import { CategoryService } from '../services/category.service';
import { Category } from '../classes/category.class';
import { SearchNameForm } from '../classes/searchform.class';

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

  constructor(private productService: ProductService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.loadCategory();
    this.loadProduct();
  }

  loadCategory(){
    this.categoryService.getAllCategory().subscribe(output=>{
      this.categories = output;
    });
  }

  loadProduct(){
    this.productService.getAllProduct().subscribe(output=>{
      this.products = output;
    });
  }

  onProductSave(){
    this.productService.saveProduct(this.product).subscribe(output=>{
      this.products.push(output);
      this.product = new Product();
    })
  }

  onSearchProduct(){
    this.products = [];
    this.productService.searchProduct(this.searchName).subscribe(output=>{
      this.products = output;
    });
  }

  onDeleteProduct(id){   
    this.productService.removeProduct(id).subscribe(output=>{
      this.loadProduct();
    });
  }

}
