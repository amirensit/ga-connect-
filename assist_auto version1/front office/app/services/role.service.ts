import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class rolesService {

    constructor(private http: Http){console.log("hhhhhhhh")}
user;
    getRoles(){
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
      return this.http.get(`http://localhost:8070/roles`,options)
        .map((res: Response) => res.json(),console.log("sandouda"));
    }

     serviceFindRole(a,b,c)
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
return this.http.get('http://localhost:8070/roles/chercherRoles?mc='
                +a+"&page="+b+"&size="+c,options).map(res=>res.json());
}


  getRole(id:number){
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
    return this.http.get('http://localhost:8070/roles/'+id,options)
      .map((res: Response) => res.json());


  }
  

    modifierRole(id : number , libelle :string , mList: Module[])
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
        
        let body ={ "id":id,"libelle" : libelle, "modules" : mList};
        return this.http.put('http://localhost:8070/roles/'+id,body,options) .map(res=>res.json());
    }
   

  deleteRole(id) {
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
    return this.http.delete('http://localhost:8070/roles/'+id, options)
      .map(((response:Response) =>response.json()),console.log("bye"));

    
  }

   createRole(libelle:string , mList: Module[]){
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
         
 let body ={ libelle : libelle, "modules" : mList};
        return this.http.post('http://localhost:8070/roles',body, options)
            .map((res: Response) => res.json()

            );


    }
testIfRoleExist(libelle:string):Observable<boolean>{
        

        
         let body = "libelle="+libelle;
        return this.http.get('http://localhost:8070/roles/role?'+body)
           .map((res: Response) => res.json()
                );

    } 

    getModules(){
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
      return this.http.get(`http://localhost:8070/modules`,options)
        .map((res: Response) => res.json(),console.log("sandouda"));
    }
}

    interface Module {
    id : number;
    typeModule : string;
   

}

