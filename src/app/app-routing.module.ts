import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdashboardComponent } from './adashboard/adashboard.component';
import { AddbookComponent } from './addbook/addbook.component';
import { AeditbookComponent } from './aeditbook/aeditbook.component';
import { AviewbookComponent } from './aviewbook/aviewbook.component';
import { BookslistComponent } from './bookslist/bookslist.component';
import { LoginComponent } from './login/login.component';
import { RdashboardComponent } from './rdashboard/rdashboard.component';
import { RegisterComponent } from './register/register.component';
import { RviewbookComponent } from './rviewbook/rviewbook.component';

const routes: Routes = [
  {path : '', component: LoginComponent},
  {path : 'login', component: LoginComponent},
  {path : 'register', component: RegisterComponent},
  {path:'rdashboard',component:RdashboardComponent},
  {path:'adashboard',component:AdashboardComponent},
  {path:'addbook',component:AddbookComponent},
  {path:'aviewbook/:row',component:AviewbookComponent},
  {path:'aeditbook/:row',component:AeditbookComponent},
  {path:'rviewbook/:row',component:RviewbookComponent},
  
   {path:'bookslist',component:BookslistComponent},
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
