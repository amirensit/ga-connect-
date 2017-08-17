import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import  'rxjs/add/operator/map';
import  'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import {Headers,RequestOptions} from '@angular/http';
import 'rxjs/add/observable/throw';
 


@Injectable ()
export class GetsService {

 

    constructor(private http: Http) {
        console.log('GetssService constructor initialised');
    }

    getProduit(n : number)
    {
        let currentUser=JSON.parse(localStorage.getItem('currentUser'));
        let login=currentUser.login;
        let password=currentUser.password;
        let headers = new Headers();
        headers.append('Authorization','Basic '+ btoa(login + ':' + password) );

      headers.append("Access-Control-Allow-Origin", "*");
      headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
      headers.append("Access-Control-Allow-Credentials", "true");
      headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


      headers.append("contentType","*");
      let options = new RequestOptions( {headers: headers });





        return this.http.get('http://localhost:8070/produits/'+n).map(res=>res.json());;
    }

    getAllProduits(){

        let currentUser=JSON.parse(localStorage.getItem('currentUser'));
        let login=currentUser.login;
        let password=currentUser.password;
        let headers = new Headers();
        headers.append('Authorization','Basic '+ btoa(login + ':' + password) );

      headers.append("Access-Control-Allow-Origin", "*");
      headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
      headers.append("Access-Control-Allow-Credentials", "true");
      headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


      headers.append("contentType","*");
      let options = new RequestOptions( {headers: headers });



        return this.http.get('http://localhost:8070/produits/')
        .map(res=>res.json());
    }

    ajouterProduit(designation:string ,prix:number ,quantite: number){
     
        let body ={ "designation" : designation, "prix" : prix, "quantite" : quantite};
        return this.http.post('http://localhost:8070/produits',body) .map(res=>res.json());
        
       
    }


    supprimerProduit(n : number){
     
        
        return this.http.delete('http://localhost:8070/produits/'+n) .map(res=>res.json());
        
       
    }


    modifierProduit(n : number,designation:string ,prix:number ,quantite: number){
     
          let body ={ "designation" : designation, "prix" : prix, "quantite" : quantite};
        return this.http.put('http://localhost:8070/produits/'+n,body) .map(res=>res.json());
        
       
    }





serviceFindProduct(a,b,c)
{
return this.http.get("http://localhost:8070/chercherProduits?mc="
				+a+"&page="+b+"&size="+c).map(res=>res.json());
}



}