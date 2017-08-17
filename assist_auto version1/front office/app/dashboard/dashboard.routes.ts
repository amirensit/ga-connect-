import { Route } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './client/home.component';

import { PackComponent } from './pack/pack.component';
import { GouvernoratComponent } from './gouvernorat/gouvernorat.component';

import { MarqueComponent } from './marque/marque.component';
import { VilleComponent } from './ville/ville.component';

import { TypeServiceComponent } from './typeService/typeService.component';
import { RemorqueurComponent } from './remorqueur/remorqueur.component';
import { UpgradeComponent } from './upgrade/upgrade.component';
import {HelloComponent} from './hello/hello.component';
import {UtilisateurComponent} from './utilisateur/home.component';
import {RoleComponent} from './role/role.component';
import {ContratComponent} from './contrat/contrat.component';
import {LoginComponent} from "../login/login.component";
import {AuthGuard} from "../guards/auth.guard";


export const MODULE_ROUTES: Route[] =[
    { path: 'login', component: LoginComponent },
     {path : 'roles' , component : RoleComponent, canActivate: [AuthGuard]},
    {path : 'utilisateurs' , component : UtilisateurComponent, canActivate: [AuthGuard]},
    { path: 'clients', component: HomeComponent, canActivate: [AuthGuard] },
    
    { path: 'packs', component: PackComponent, canActivate: [AuthGuard] },
    
    { path: 'marques', component: MarqueComponent, canActivate: [AuthGuard] },
   // { path: 'gouvernorats', component: GouvernoratComponent },
   // { path: 'villes', component: VilleComponent },
    
    { path: 'typeService', component: TypeServiceComponent, canActivate: [AuthGuard] },
    { path: 'remorqueurs', component: RemorqueurComponent, canActivate: [AuthGuard] },
     { path: 'contrats', component: ContratComponent, canActivate: [AuthGuard] },
    
    
    { path: '', redirectTo: 'login', pathMatch: 'full', canActivate: [AuthGuard] }
]

export const MODULE_COMPONENTS = [
    LoginComponent,
    RoleComponent,
    UtilisateurComponent,
    HomeComponent,
    //HelloComponent,
    PackComponent,
    
    MarqueComponent,
    //GouvernoratComponent,
    //VilleComponent,
   
    TypeServiceComponent,
    RemorqueurComponent,
    ContratComponent
    
    
]
