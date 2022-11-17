import { Component, OnInit, Optional, ViewChild } from '@angular/core';

import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Book } from '../book';
import { DigitalService } from '../digital.service';


@Component({
  selector: 'app-rdashboard',
  templateUrl: './rdashboard.component.html',
  styleUrls: ['./rdashboard.component.scss']
})
export class RdashboardComponent implements OnInit {

  booklist: Array<Book> = [];
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['id', 'fullName', 'email', 'entryType','streamType','action'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _route: Router, private _service: DigitalService,
    @Optional() private dialog:MatDialog) { }

  ngOnInit(): void {
    this.getStudents();
  }
  viewBooks(row:any){
    this._route.navigate(['rviewbook/'+row]);
  }
//bookslist

bookslist(){
  this._route.navigate(['/rdashboard']);
    }

//fetchsubsrbedbooks
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
}
