import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Book } from '../book';
import { DigitalService } from '../digital.service';

@Component({
  selector: 'app-aviewbook',
  templateUrl: './aviewbook.component.html',
  styleUrls: ['./aviewbook.component.scss']
})
export class AviewbookComponent implements OnInit {

  bookF!:FormGroup;
  book1=new Book();
  constructor(private _route: Router,private _service:DigitalService,
    private formBuilder:FormBuilder,private routeA:ActivatedRoute) { }

  ngOnInit(): void {
    this.bookF=this.formBuilder.group({
      bookId:['',Validators.required],
      bookTitle:['',Validators.required],
      bookAuthorId:['',Validators.required],
      bookPublisher:['',Validators.required],
      bookCategory:['',Validators.required],
      bookContent:['',Validators.required],
      bookPrice:['',Validators.required],
      bookStatus:['',Validators.required],
      bookSubscribe:['',Validators.required],
      bookSubscribeId:['',Validators.required],
      bookReleaseDate:['',Validators.required],bookImage:['',Validators.required],
    });
    let id=this.routeA.snapshot.paramMap.get('row');
   // var a:number= +id;
    this.viewBooks(parseInt(id));
  }
  homPage(){
    this._route.navigate(['/adashboard']);
      }
      viewBooks(id:number){
        this._service.viewBook(id).subscribe(
          {
            next:(res)=>{
              this.book1=res;
              console.log("view oage"+res+":"+this.book1.bookCategory);
              this.bookF.controls['bookId'].setValue(this.book1.bookId);
            this.bookF.controls['bookTitle'].setValue(this.book1.bookTitle);
            this.bookF.controls['bookPublisher'].setValue(this.book1.bookPublisher);
            this.bookF.controls['bookCategory'].setValue(this.book1.bookCategory);
            this.bookF.controls['bookContent'].setValue(this.book1.bookContent);
            this.bookF.controls['bookPrice'].setValue(this.book1.bookPrice);
            this.bookF.controls['bookReleaseDate'].setValue(this.book1.bookReleaseDate);
            this.bookF.controls['bookSubscribeId'].setValue(this.book1.bookSubscribeId);
            this.bookF.controls['bookSubscribe'].setValue(this.book1.bookSubscribe);
            this.bookF.controls['bookStatus'].setValue(this.book1.bookStatus);
            this.bookF.controls['bookAuthorId'].setValue(this.book1.bookAuthorId);
            },error:(err)=> { 
              alert("Exception occurred 1")}
          })
      }
}
