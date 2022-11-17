import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DigitalService } from '../digital.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  userName!:string;
  user=new User();
  image="/assets/images/bookim.jpeg"
  constructor(private userservice: DigitalService, private route:Router) { }

  ngOnInit(): void {
  }
  userLogin()
  {
    console.log(this.user);
    this.userservice.loginUser(this.user).subscribe(data=>{
     debugger;
     console.log("User Info: ", data);
     let user:any= data;
     if(data  != null){
      localStorage.setItem('userrole', user?.role);
      let user_role = localStorage.getItem('userrole');
      if(user_role === "AUTHOR"){
           alert("Login SuccessFully");
      this.route.navigate(['/adashboard'])
      }
      else{
      
      this.route.navigate(['/rdashboard'])
      }
     }
      

      
    }, error=>alert("please enter correct username and password"));
  }
}

