import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../classes/category.class';
import { Router } from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];
  category: Category = new Category();

  constructor(private progressService: NgProgressService,private categoryService: CategoryService, private router: Router) { }

  ngOnInit() {       
    this.loadCategory();
  }

  loadCategory(){      
    this.progressService.start();
    this.categoryService.getAllCategory().subscribe(output=>{       
      this.progressService.done();
      this.categories = output;
    },error=>{      
      this.progressService.done();
      console.log(error);
    });    
  }  

  onCategorySave(){
    this.progressService.start();
    this.categoryService.saveCategory(this.category).subscribe(output=>{
      this.progressService.done();
      this.category = new Category();
      this.categories.push(output);  
    },error=>{
      this.progressService.done();
      console.log(error);
    });
  }
}
