import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../classes/category.class';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.loadCategory();
  }

  loadCategory(){
    this.categoryService.getAllCategory().subscribe(output=>{
        this.categories = output;
    },error=>{
      console.log(error);
    },()=>{
      console.log("finish load category");
    });
  }  
}
