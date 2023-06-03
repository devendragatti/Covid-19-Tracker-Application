import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ccovidservice } from '../ccovidservice.service';
import { User } from '../User';
@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {
  user:User
  disp_msg: string;
  constructor(private ccovidservice: ccovidservice, private router: Router) { 
    this.user= new User()
  }

  ngOnInit(): void {
  }
  changedetails(){
    console.log(this.user)
    console.log(environment.username+"inedit")
    this.ccovidservice.changedetails(this.user,environment.username).subscribe({
      next:(data)=>{this.disp_msg="Congratulatins "+this.user.userName+" your details changed successfully";this.logout()
      //this.router.navigate(["/header"])
    },
      error:(e)=>{console.log(e);this.disp_msg="Failed to change your account details"
      ;
      }
    })
  }
  logout(){
    console.log("heyy...I am logout")
    this.ccovidservice.logout();
   this.router.navigate(['/login']);

  }
}
