import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from "../services/authentication.service";
import {utilisateursService} from "../services/utilisateur.service";
import {Role} from "../model/role";



@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
  
    
})

export class LoginComponent implements OnInit {
    model: any = {};
    modal: any = {};
    loading = false;
    error = '';
    users:any;
    role:Role;
    selectedrole: any={};
    private currentUser: any;
    x:1;
    r:any;
t:boolean;
h:boolean;
utilisateurExistError='';
passExistError='';
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,private  utilisateurService: utilisateursService) { }

    ngOnInit() {
        // reset login status
      //  this.logout();
       this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    login() {
        
        this.loading = true;
        this.authenticationService.login(this.model.email, this.model.motDePasse)
            .subscribe ( result1 => {
                if (result1 !=null) {
                        console.log(result1);
                                this.router.navigate(['/roles']);
                                window.location.reload();
                console.log();

                }

                     
                else {
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }


            },
            error => {
                    //this.error = 'Username or password is incorrect';
                   
                    //console.log("errorrrr");
                    this.loading = false;
                    //this.model.username='';
                    //this.model.password='';
                    //this.router.navigate(['login']);
                    this.utilisateurService.testIfUtilisateurExist(this.model.email)
                    .subscribe ( res =>{if (res==false) { this.t=true;this.h=false;this.loading = false;
                        console.log(this.t);
                        this.error  ="Email incorrect";
                        console.log("mail")} else{
     this.utilisateurService.testMotDePasse(this.model.motDePasse,this.model.email)
     .subscribe(r => { if (r==false) { this.h=true;this.t=false;console.log(this.h);this.loading = false;this.passExistError ="mot de passe incorrecte";console.log("pass")
                                      }
                     }
                        );}}
                        );

                }


            );
    }

logout(id){
this.authenticationService.logout(id).subscribe(result=>{this.model=result;
                        console.log(this.model);});


}




}

            


                
               


            
    

            

