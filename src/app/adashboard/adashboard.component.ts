import { Component, OnInit, Optional, ViewChild } from '@angular/core';
import { Book } from '../book';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DigitalService } from '../digital.service';
import { Router } from '@angular/router';
import { AddbookComponent } from '../addbook/addbook.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
2
@Component({
  selector: 'app-adashboard',
  templateUrl: './adashboard.component.html',
  styleUrls: ['./adashboard.component.scss']
})
export class AdashboardComponent implements OnInit {
  book: Array<Book> = [];
  displayedColumns: string[] = ['id', 'fullName', 'email', 'entryType','streamType','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor( private _route: Router,private _service:DigitalService,
    @Optional() private dialogRef:MatDialogRef<AddbookComponent>,
    @Optional() private dialog:MatDialog) { }
   
  ngOnInit(): void {
    this.getStudents();
  }
  openBook(){
    console.log("Opening Book");
    this._route.navigate(['addbook']);
  }
  editStudent(row:any){
    console.log("Editing the data");
    this._route.navigate(['aeditbook/'+row]);
  }
  viewBooks(row:any){
    this._route.navigate(['aviewbook/'+row]);
  }
  getStudents() {
    this._service.allBookDetails().subscribe(
       {
         next:(res)=>{
           this.dataSource=new MatTableDataSource(res)
           this.dataSource.paginator=this.paginator
           this.dataSource.sort=this.sort
         },error:(err)=> { 
           alert("Exception occurred 1")}
       })
      }
      logout(){
        this._route.navigate(['/login']);
      }
  isEmpty()
  {
    if (this.book == null)
    {
      return true;
    }
    else { return false; }
  }
  }