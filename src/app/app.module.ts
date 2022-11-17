import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatCardModule} from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { AppComponent } from './app.component';
import {MatPaginator} from '@angular/material/paginator';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import {MatDialog,MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import {FormBuilder,FormGroup,Validator,Validators} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import { HttpClientModule } from '@angular/common/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AddbookComponent } from './addbook/addbook.component';
import { AdashboardComponent } from './adashboard/adashboard.component';
import { AeditbookComponent } from './aeditbook/aeditbook.component';
import { AviewbookComponent } from './aviewbook/aviewbook.component';
import { RdashboardComponent } from './rdashboard/rdashboard.component';
import { BookslistComponent } from './bookslist/bookslist.component';
import { RsubsribeComponent } from './rsubsribe/rsubsribe.component';
import { RviewbookComponent } from './rviewbook/rviewbook.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AddbookComponent,AdashboardComponent,AeditbookComponent,AviewbookComponent, RdashboardComponent, BookslistComponent, RsubsribeComponent, RviewbookComponent
  ],
  imports: [
    BrowserModule,MatTabsModule,MatFormFieldModule,MatSelectModule,FormsModule,FormsModule,ReactiveFormsModule,
    AppRoutingModule,MatButtonModule,HttpClientModule,MatToolbarModule,MatTableModule,
    MatCardModule, MatIconModule,BrowserAnimationsModule,MatInputModule,MatCheckboxModule,
    MatRadioModule,MatPaginatorModule
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
