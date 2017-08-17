import { Component } from '@angular/core';
import { AllProducts } from './components/allProducts.component';
import {Home} from './components/home.component';
import{AuthGuard} from './guard/auth.guard';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  
  
   
})
export class AppComponent {
  title = 'app';
}
