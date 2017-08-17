import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class utilisateursService {

    constructor(private http: Http){console.log("hhhhhhhh")}
user;
    getUtilisateurs(){
      console.log("hi");
       //let currentUser = JSON.parse(localStorage.getItem('currentUser'));

        //let login=  currentUser.login;
        //let password= currentUser.password;
        let headers = new Headers();

       // headers.append('Authorization','Basic '+ btoa(login + ':' + password) );

   

      headers.append("Access-Control-Allow-Origin", "*");
      headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
      headers.append("Access-Control-Allow-Credentials", "true");
      headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


      headers.append("contentType","*");
      let options = new RequestOptions( {headers: headers });
      return this.http.get(`http://localhost:8070/utilisateurs`,options)
        .map((res: Response) => res.json(),console.log("sandouda"));
    }

     serviceFindUtilisateur(a,b,c)
{
 // let currentUser = JSON.parse(localStorage.getItem('currentUser'));

       // let login=  currentUser.login;
        //let password= currentUser.password;
        let headers = new Headers();

       // headers.append('Authorization','Basic '+ btoa(login + ':' + password) );

   

      headers.append("Access-Control-Allow-Origin", "*");
      headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
      headers.append("Access-Control-Allow-Credentials", "true");
      headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


      headers.append("contentType","*");
      let options = new RequestOptions( {headers: headers });
return this.http.get('http://localhost:8070/utilisateurs/chercherUtilisateurs?mc='
                +a+"&page="+b+"&size="+c,options).map(res=>res.json());
}

getUtilisateur(id){
      console.log("cc");
    let headers = new Headers();

    headers.append("Access-Control-Allow-Origin", "*");
    headers.append("Access-Control-Allow-Headers",
      "origin, content-type, accept, authorization");
    headers.append("Access-Control-Allow-Credentials", "true");
    headers.append("Access-Control-Allow-Methods",
      "GET, POST, PUT, DELETE, OPTIONS, HEAD");


    headers.append("contentType","*");
    let options = new RequestOptions( {headers: headers });
    return this.http.get('http://localhost:8070/utilisateurs/'+id,options)
      .map((res: Response) => res.json());


  }

  deleteUtilisateur(id):Observable<boolean>{
       console.log("okk");
    
    let headers = new Headers();
    headers.append("Access-Control-Allow-Origin", "*");
    headers.append("Access-Control-Allow-Headers",
      "origin,session-variable, content-type, accept, authorization");
    headers.append("Access-Control-Allow-Credentials", "true");
    headers.append("Access-Control-Allow-Methods",
      "GET, POST, PUT, DELETE, OPTIONS, HEAD");


    headers.append("contentType","*");


    let options = new RequestOptions( {headers: headers });
    console.log("ccgggggc");
    return this.http.delete('http://localhost:8070/utilisateurs/'+id, options)
      .map(((response:Response) =>{return true}),console.log("bye"));

    
  }

  debloquerUtilisateur(id:number):Observable<boolean>{
        console.log("aaaaaaa");
        
       
        let headers = new Headers();

       
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        headers.append("contentType","*");
       
        let options = new RequestOptions( {headers: headers });
        return this.http.put('http://localhost:8070/utilisateurs/debloquer/'+id, options)
            .map((res: Response) => res.json(), console.log("bye"));
    }

bloquerUtilisateur(id:number):Observable<boolean>{
        console.log("aaaaaaa");
        
       
        let headers = new Headers();

      
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        headers.append("contentType","*");
       
        let options = new RequestOptions( {headers: headers });
        return this.http.put('http://localhost:8070/utilisateurs/bloquer/'+id, options)
            .map((res: Response) => res.json(), console.log("bye"));
    }
testIfUtilisateurExist(email:string):Observable<boolean>{
        

        
         let body = "email="+email;
        return this.http.get('http://localhost:8070/utilisateurs/utilisateur?'+body)
           .map((res: Response) => res.json()
                );

    } 

    testMotDePasse(motDePasse:string,email:string):Observable<boolean>{
        

        
         let body = "motDePasse="+motDePasse+"&email="+email;;
        return this.http.get('http://localhost:8070/utilisateurs/utilisateur/password?'+body)
           .map((res: Response) => res.json()
                );

    } 
    createUtilisateur(nom:string , prenom:string,email:string, motDePasse:string, numFixe:number,numMobile:number,status:boolean,
     blocage:boolean,connect:boolean,rList: Role[]){
        console.log("aaaaaaa");

       
       

       
        let headers = new Headers();

       
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType","*");

        //console.log(body);
        let options = new RequestOptions( {headers: headers });
         
 let body ={ nom : nom,prenom : prenom,email : email,motDePasse : motDePasse,numFixe : numFixe,numMobile : numMobile,status : status,
   blocage : blocage,connect : connect, roles : rList};
        return this.http.post('http://localhost:8070/utilisateurs',body, options)
            .map((res: Response) => res.json()

            );


    }

     modifierUtilisateur(id : number , nom:string , prenom:string,email:string, motDePasse:string, numFixe:number,numMobile:number,status:boolean,
     blocage:boolean,connect:boolean,rList: Role[])
    {

          let headers = new Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        headers.append("contentType","*");
        let options = new RequestOptions( {headers: headers });
        
        let body ={ "id":id,"nom" : nom,"prenom" : prenom,"email" : email,"motDePasse" : motDePasse,"numFixe" : numFixe,
        "numMobile" : numMobile,"status" : status,"blocage" : blocage,"connect" : connect, "roles" : rList};
        return this.http.put('http://localhost:8070/utilisateurs/'+id,body,options) .map(res=>res.json());
    }

}

interface Role {
    id : number;
   libelle:string;
    modules:Module[];

}

interface Module {
    id : string;
    typeModule : string;
   

}
