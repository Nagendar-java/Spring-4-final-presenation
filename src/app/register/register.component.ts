import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DigitalService } from '../digital.service';
import { User } from '../user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  user:User=new User;
  constructor(private _service:DigitalService, private route:Router) { }

  ngOnInit(): void {
  }
  addUser(){

    this._service.addUser(this.user).subscribe(data=>{
      alert("User Added SuccessFully");
     this.route.navigate(['/login'])
    }, error=>alert("please enter correct username and password"));
    }

}