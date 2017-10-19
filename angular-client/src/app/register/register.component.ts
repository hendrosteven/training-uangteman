import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { UserService } from '../services/users.service';
import { Users } from '../classes/users.class';
import { Router } from '@angular/router';
import {ToastsManager} from 'ng2-toastr/ng2-toastr';
import {NgProgressService} from 'ngx-progressbar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: Users = new Users();
  success: boolean = false;
  error: boolean = false;
  errors: string[] = [];

  constructor(private progressService: NgProgressService,private userService:UserService, private router: Router,
    private toastr: ToastsManager, private vcr: ViewContainerRef) { 
      this.toastr.setRootViewContainerRef(vcr);
    }

  ngOnInit() {
  }

  onRegister(){
    this.progressService.start();
    this.userService.register(this.user).subscribe(output=>{
      this.progressService.done();
      this.error = false;
      this.success = true;
      this.user = new Users();
      this.toastr.success('Hooree you are awesome','Success');
    },error=>{    
      this.progressService.done();
      this.success = false;      
      this.errors = error.messages;
      this.error = true;      
      this.toastr.error('This is not good!','Errors');
    });
  }


  goToLogin(){
    this.router.navigate(['login']);
  }

}
