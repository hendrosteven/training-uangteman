import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import { CategoryService } from './services/category.service';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ProductComponent } from './product/product.component';
import { ProductService } from './services/product.service';

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    ProductComponent
  ],
  imports: [
    HttpModule,
    FormsModule,
    BrowserModule
  ],
  providers: [CategoryService,ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
