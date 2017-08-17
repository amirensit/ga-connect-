import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';


import { AllProducts } from './components/allProducts.component';
import {LoginComponent} from './components/authentification.component'
import {Home} from './components/home.component'
import {Add} from './components/add.component'
import {Supprimer} from './components/supprimer.component'
import {Modifier} from './components/modifier.component'
import {Pagination} from './components/pagination.component'
import{AuthGuard} from './guard/auth.guard';


const appRoutes: Routes = [
    {
        path:'',
        redirectTo:'/login',
        pathMatch: 'full',
        
        
    },
    {
        path:'add' ,
        canActivate:[AuthGuard],
        component: Add
    },
    {
        path:'delete', 
       
        component: Supprimer
    },
    {
        path:'edit',
    
        component: Modifier
    },
    {
        path:'pagination',
        
        component: Pagination
    },
    {
        path:'login',
        component: LoginComponent
    },
    
    {
        path: 'allProduct',
        
        component: AllProducts
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);