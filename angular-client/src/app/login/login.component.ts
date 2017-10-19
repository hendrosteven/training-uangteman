import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/users.service';
import { LoginForm } from '../classes/loginform.class';
import { Router } from '@angular/router';
import {Md5} from 'ts-md5/dist/md5';
import {NgProgressService} from 'ngx-progressbar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: LoginForm = new LoginForm();
  error: boolean = false;

  constructor(private progressService: NgProgressService,private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  onLogin(){   
    this.progressService.start();
    this.userService.login(this.loginForm).subscribe(output=>{
      this.progressService.done();
        let hash = Md5.hashStr(this.loginForm.password).toString();
        let token = btoa(this.loginForm.email + ':' + hash);
        localStorage.setItem('token',token);
        console.log('token: '+token);
        this.router.navigate(['category']);
    },error=>{
      this.progressService.done();
      this.error = true;
      console.log(error);
    });
  }
}
