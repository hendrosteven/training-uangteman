import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import { CategoryService } from './services/category.service';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ProductComponent } from './product/product.component';
import { ProductService } from './services/product.service';
import { RouterModule } from '@angular/router';
import { UserService } from './services/users.service';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guard/auth.guard';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastModule} from 'ng2-toastr/ng2-toastr';
import { NgProgressModule } from 'ngx-progressbar';

export const AppRoutes : any = [
  {path: "", component: AppComponent},
  {path: "category", component: CategoryComponent, canActivate:[AuthGuard]},
  {path: "product", component: ProductComponent, canActivate:[AuthGuard]},
  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    ProductComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    HttpModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    NgProgressModule,
    ToastModule.forRoot(),
    RouterModule.forRoot(AppRoutes,{useHash: true})
  ],
  providers: [CategoryService,ProductService, UserService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
