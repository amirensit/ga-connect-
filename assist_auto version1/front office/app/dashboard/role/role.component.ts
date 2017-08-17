import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';
import initDemo = require('../../../assets/js/charts.js');
import {rolesService} from '../../services/role.service';
import {Role} from "../../model/role";
//import {Module} from "../../model/module";
import { FormControl, FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { Router } from "@angular/router";

@Component({
  
    moduleId: module.id,
    templateUrl: 'role.component.html',
     providers: [rolesService],
})

export class RoleComponent implements OnInit{
    private data;
    //moduleFilter: Module ;
roleFilter: Role = new Role();
roles ;
modules ;
re;
x:any;
t:boolean;
    y:any;
    sv:boolean=false;
 selectedrole: any={};
pageRoles;
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
  pListPackForAjoutClient: Module[]=[];
  test:boolean;
  n:any;
  ok;
  m: Module[];
  libelle:string;
  Role :any= {};
loading = false;
 myForm: FormGroup;
     constructor(private  roleService: rolesService,private route :Router){}
   
    ngOnInit(){
        this.getRoles();
        this.getModules();
       
    }
chercherRoles()
{
    this.roleService.serviceFindRole(this.motCle,this.pageCourante,this.size)
    .subscribe  (response =>
              {
                
            this.pageRoles=response.content;
            this.pages=new Array(response.totalPages);

                
              });
}
  getRoles() {
    this.pageCourante=0;
    this.chercherRoles();

  }

  goToPage(p)
{
this.pageCourante=p;
this.chercherRoles();


}

 getRole(id){
            this.rolee.libelle="";
           
            this.rolee = this.roleService.getRole(id).subscribe(
                res => {this.rolee = res,console.log(this.rolee)},
                error => console.error(error)
            );
        }

deleteRole(id) {
        console.log("jit");
        this.loading = false;
         this.r = this.roleService.getRole(id).subscribe(
            r => {this.r = r;
       
            this.roleService.deleteRole(r.id).subscribe(
                res => {this.d=res,this.getRoles(),this.loading = true; console.log(res);},
                error => {
                    alert("Could not delete project.");
                    console.error('error:', error)
                }
            );
        
    },
 error => console.error(error));
}
   
   
   //deleteRole(id) {
    //console.log("jit");
  
    //this.roleService.deleteRole(id).subscribe(
    //  res =>{ this.re=res,this.getRoles(),
    //    console.log(id);
    //  console.log(res);},
    // error => {alert("Could not delete product."); console.error('error:', error)}
    //);
 // }
  
   selectedRole(Role :any){
        this.sv=true;
        this.selectedrole = Role ;
         this.x= this.selectedrole.id;
         this.roleDTO={id:this.x};
        this.y= this.selectedrole.libelle;
    }


    testIfRoleExist(){
                this.roleService.testIfRoleExist(this.Role.libelle)
                    .subscribe(result=>{
                        if(result!=null)
                        {this.roleExistError = "Ce role existe déja ! ";
                        console.log(this.roleExistError);
                         // this.flashMessage.show('aaaaaaaaa', {cssClass:'alert-danger', timeout:3000});
                            this.loading = false ;
                        return true ;}
                        else {
                            
                        return false  ;}
                    }
                 
                    ) ;
                //return true ;
                    }



         createRole() {
     
        this.spinning=true;
       
          
    this.roleService.testIfRoleExist(this.Role.libelle)
                    .subscribe(result=>{this.role=result;
                        console.log(this.role);
                        if(this.role==false)
                        {
                          this.test=true;  
                          
                            this.roleService.createRole(this.Role.libelle,this.Role.modules)
            .subscribe(
                res => {
                    this.test=true;
                     this.Role.libelle=null;
                 
                   this.spinning=false;this.roles,console.log(this.roles);
                    
              
                },
                err => console.error(err)
         
            );}
                        
                        else {
                          
                            this.test=false;
                           
                            this.t=false,
                             this.spinning=false;
                            this.roleExistError = "Ce role existe déjà! ";
                        console.log(this.roleExistError);
                         
                            this.loading = false ;

                        return true ;}
                        }
                    
                 
                    ) ;



        
        console.log("cv");
    }

    ajoutPackToClient(p : Module,isChecked: boolean)
{
    if(isChecked) {
    this.pListPackForAjoutClient.push(p);
  } else {
    let index = this.pListPackForAjoutClient.findIndex(x => x.id == p.id);
    this.pListPackForAjoutClient.splice(index,1);

   
  }
   console.log(this.pListPackForAjoutClient);
}


ajouterRole()
{
    this.spinning=true;
       
          
    this.roleService.testIfRoleExist(this.Role.libelle)
                    .subscribe(result=>{this.role=result;
                        console.log(this.role);
                        if(this.role==false)
                        {
                          this.test=true;  
    this.roleService.createRole(this.Role.libelle,this.pListPackForAjoutClient).subscribe( posts =>
              {this.ok=posts;
                  this.Role.libelle=null;
                 
                   this.spinning=false;this.roles,console.log(this.ok);
                 });}else {
                          
                            this.test=false;
                           
                            this.t=false,
                             this.spinning=false;
                            this.roleExistError = "Ce role existe déjà! ";
                        console.log(this.roleExistError);
                         
                            this.loading = false ;

                        return true ;}
                        }
                    
                 
                    ) ;



        
        console.log("cv");
    }



recupererIdClient(a : number)
    {
        this.n=a;
    }




 updateRole(){
     
        
        this.roleService.modifierRole(this.rolee.id,this.rolee.libelle,this.pListPackForAjoutClient)
            .subscribe(
                res =>{this.rolee,console.log(this.rolee)},
                error => console.error(error)
            );
      
    }
validerEditRole(n)
{
    
  this.roleService.modifierRole(n,this.libelle  ,this.m)
            .subscribe ( posts =>
              {
                 })



}
getModules() {
        //let b:any;
        //this.execut.endTime="";
       //if(this.User.typeRole!== 1){
            //this.route.navigate(['/gererusers']);
             
        this.roleService.getModules().subscribe(
            response=> {
                //console.log(this.User.typeRole);
                this.modules = response;
                console.log(this.modules);


            },

            error =>{}
        );
   // }else{
            // alert("hhhhhhhhhhhhhhhhhhh");

        //}

    }
           

}
 interface Module {
    id : number;
    typeModule : string;
   

}
