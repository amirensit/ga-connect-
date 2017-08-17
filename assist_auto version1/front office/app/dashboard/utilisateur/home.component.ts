import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';
import initDemo = require('../../../assets/js/charts.js');
import {utilisateursService} from '../../services/utilisateur.service';
import {rolesService} from '../../services/role.service';
import {Role} from "../../model/role";
import { Router } from "@angular/router";
import {Utilisateur} from "../../model/utilisateur";

@Component({
     
    moduleId: module.id,
    templateUrl: 'home.component.html',
     providers: [utilisateursService],
})

export class UtilisateurComponent implements OnInit{
roles ;
roleFilter: Role = new Role();
utilisateurFilter: Utilisateur = new Utilisateur();
u:any={};
teste : boolean = true ;
modules ;
utilisateuur: any={};
re;
Utilisateur :any= {};
x:any;
t:boolean;
    y:any;
    sv:boolean=false;
 selectedrole: any={};
pageUtilisateurs;
 roleDTO:any={};
motCle:string="";
pageCourante:number=0;
size:number=4;
pages:any[];
 r: any={};
 rolee: any={};
  spinning:boolean=false;
 roleExistError='';
 role: any={};
  d: boolean;
  ok;
  utilisateur: any={};
   utilisateurExistError='';
  test:boolean;
  Role :any= {};
  utilisateurs;
  pListPackForAjoutClient: Role[]=[];
loading = false;
nouveaupass: string;
nouveaupass1: string;
     constructor(private  utilisateurService: utilisateursService,private  rolesService: rolesService,private route :Router){}
   
    ngOnInit(){
        this.getUtilisateurs();
        this.getRoles();
       
       
    }
chercherUtilisateurs()
{
    this.utilisateurService.serviceFindUtilisateur(this.motCle,this.pageCourante,this.size)
    .subscribe  (response =>
              {
                
            this.pageUtilisateurs=response.content;
            
            this.pages=new Array(response.totalPages);

                
              });
}
  getUtilisateurs() {
    this.pageCourante=0;
    this.chercherUtilisateurs();

  }

  goToPage(p)
{
this.pageCourante=p;
this.chercherUtilisateurs();


}

 ajoutPackToClient(p : Role,isChecked: boolean)
{
    if(isChecked) {
    this.pListPackForAjoutClient.push(p);
  } else {
    let index = this.pListPackForAjoutClient.findIndex(x => x.id == p.id);
    this.pListPackForAjoutClient.splice(index,1);

   
  }
   console.log(this.pListPackForAjoutClient);
}
ajouterUtilisateur()
{
        this.spinning=true;
       
          
    this.utilisateurService.testIfUtilisateurExist(this.Utilisateur.email)
                    .subscribe(result=>{this.utilisateur=result;
                        console.log(this.utilisateur);
                        if(this.utilisateur==false)
                        {
                          this.test=true;  
       
          if(( this.nouveaupass==this.nouveaupass1   ) )
{    this.Utilisateur.motDePasse=this.nouveaupass1; 
      
    this.utilisateurService.createUtilisateur(this.Utilisateur.nom, this.Utilisateur.prenom,this.Utilisateur.email, this.Utilisateur.motDePasse,
     this.Utilisateur.numFixe,this.Utilisateur.numMobile,this.Utilisateur.status,
     this.Utilisateur.blocage,this.Utilisateur.connect,this.pListPackForAjoutClient).subscribe( posts =>
              {this.ok=posts;window.location.reload();
                 
                       
                 
                   this.spinning=false;this.utilisateurs,console.log(this.ok);
                 });}}else {
                          
                            this.test=false;
                           
                            this.t=false,
                             this.spinning=false;
                            this.utilisateurExistError = "Cet utilisateur existe déjà! ";
                        console.log(this.utilisateurExistError);
                         
                            this.loading = false ;

                        return true ;}
                        }
                    
                 
                    ) ;



        
        console.log("cv");
    }

     getUtilisateur(id){
            this.utilisateuur.nom="";
             this.utilisateuur.prenom="";
              this.utilisateuur.email="";
               this.utilisateuur.motDePasse="";
                this.utilisateuur.numFixe="";
                 this.utilisateuur.numMobile="";
                  this.utilisateuur.status="";
                   this.utilisateuur.blocage="";
                    this.utilisateuur.connect="";
           
            this.utilisateuur = this.utilisateurService.getUtilisateur(id).subscribe(
                res => {this.utilisateuur = res,console.log(this.utilisateuur)},
                error => console.error(error)
            );
        }

    updateUtilisateur(){
     
        
        this.utilisateurService.modifierUtilisateur(this.utilisateuur.id,this.utilisateuur.nom, this.utilisateuur.prenom,this.utilisateuur.email, this.utilisateuur.motDePasse,
     this.utilisateuur.numFixe,this.utilisateuur.numMobile,this.utilisateuur.status,
     this.utilisateuur.blocage,this.utilisateuur.connect,this.pListPackForAjoutClient)
            .subscribe(
                res =>{this.utilisateuur,console.log(this.utilisateuur)},
                error => console.error(error)
            );
      
    }

 bloquerUtilisateur(id:number){

       this.u = this.utilisateurService.getUtilisateur(id).subscribe(
            res=>{this.u=res}
             
            );
            if (this.u.blocage==false){(<HTMLInputElement>document.getElementById('checkme-'+id)).checked==true} 
            else{(<HTMLInputElement>document.getElementById('checkme-'+id)).checked==false}
        
        
       
        
      
if ((<HTMLInputElement>document.getElementById('checkme-'+id)).checked==false){
             (this.utilisateurService.bloquerUtilisateur(id).subscribe(re =>{console.log("lll")}));
             //(<HTMLInputElement>document.getElementById('text-'+id)).disabled == false ;
            // this.teste=false;
             //console.log("hhhhhhhhh");
        } else{
            (this.utilisateurService.debloquerUtilisateur(id).subscribe(re =>{console.log("lllmm")}));
             //(<HTMLInputElement>document.getElementById('text-'+id)).disabled == true ;
            this.teste=true;
            console.log("hhhhhkkkkh");
        }

        
        
}



  check(id:number){
        this.u = this.utilisateurService.getUtilisateur(id).subscribe(
            res=>{this.u=res},
            err=>console.log("ok"));
            if (this.u.blocage==false){
          return true}
          else{return false}
        }

        getRoles() {
        //let b:any;
        //this.execut.endTime="";
       //if(this.User.typeRole!== 1){
            //this.route.navigate(['/gererusers']);
             
        this.rolesService.getRoles().subscribe(
            response=> {
                //console.log(this.User.typeRole);
                this.roles = response;
                console.log(this.roles);


            },

            error =>{}
        );
   // }else{
            // alert("hhhhhhhhhhhhhhhhhhh");

        //}

    }
}