import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ccovidservice } from '../ccovidservice.service';
import { User } from '../User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  constructor(private ccovidservice: ccovidservice, private router: Router) {
      this.user= new User();
     }


    disp_msg:String;

  ngOnInit(): void {
  }


  register() {
    console.log(this.user);
    this.ccovidservice.registerUser(this.user).subscribe({
      next:(data)=>{this.disp_msg="Congratulatins "+this.user.userName+" your account created successfully";this.router.navigate(["/login"])},
      error:(e)=>{console.log(e);this.disp_msg="Failed to create account ! Reason: User Already Exists with this Username"
      ;
      }
    })

  }

}