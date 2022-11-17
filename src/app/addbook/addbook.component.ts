import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { Book } from '../book';
import { DigitalService } from '../digital.service';
import { User } from '../user';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.scss']
})
export class AddbookComponent implements OnInit {
  bookF!:FormGroup;
  book=new Book();
  u=new User();
  constructor(private _route: Router,private _service:DigitalService,
    private formBuilder:FormBuilder,private routeA:ActivatedRoute) { }

  ngOnInit(): void {
    this.bookF=this.formBuilder.group({
      bookTitle:['',Validators.required],
      bookStatus:['',Validators.required],
      bookCategory:['',Validators.required],
      bookPublisher:['',Validators.required],
      bookPrice:['',Validators.required],
      bookContent:['',Validators.required],
    }); 
  }
  addbook(){
    
//this.book.bookAuthorId=this.bookF.controls['bookTitle'];
// this.book.bookTitle="book title";
// this.bookF.controls['bookTitle'].setValue(this.book.bookTitle);

this._service.createBook(1,this.bookF.value).subscribe(
  {
    next:(res)=>{
      //this.book=res;
     // this.book=this.bookF.value
console.log(this.bookF.controls['bookTitle']+":"+this.book.bookTitle+":"+res);
      console.log("Book is saved sucessfully!!"+this.bookF.value+":"+this.u.userId);
      alert("Book is saved sucessfully!!")
      this._route.navigate(['/adashboard']);
    },error:(err)=> { 
      alert("Editing book data error")}
  })
  }
  homPage(){
this._route.navigate(['/adashboard']);
  }
}
