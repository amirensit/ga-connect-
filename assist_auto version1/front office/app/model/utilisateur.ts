
import {Role} from "./role";

export class Utilisateur {

id : number ;
 nom:string ; 
 prenom:string;
 email:string;
  motDePasse:string;
   numFixe:number;
   numMobile:number;
   status:boolean;
     blocage:boolean;
     connect:boolean;
     roles: Role[];
}