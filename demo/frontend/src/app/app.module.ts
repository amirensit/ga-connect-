import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import { AppComponent } from './app.component';
import {AllProducts} from './components/allProducts.component';
import {Home} from './components/home.component';
import {Add} from './components/add.component';
import {Supprimer} from './components/supprimer.component';
import {Modifier} from './components/modifier.component';
import {Pagination} from './components/pagination.component';
import {LoginComponent} from './components/authentification.component';
import{AuthGuard} from './guard/auth.guard';


import {routing} from './app.routing';


@NgModule({
  declarations: [
    AppComponent,AllProducts,Home,Add,Supprimer,Modifier,Pagination,LoginComponent
  ],
  imports: [
    BrowserModule,FormsModule, HttpModule,routing
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
