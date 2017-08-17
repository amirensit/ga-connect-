import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    public token: string;

    constructor(private http: Http) {
        // set token if saved in local storage
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }


    login(email: string, motDePasse: string){


        let headers = new Headers();
        console.log('aaa');
        headers.append('Authorization','Basic '+ btoa(email + ':' + motDePasse) );

        console.log(headers);

        console.log('role');




        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");

//
        headers.append("contentType","application/json");

        let options = new RequestOptions({ headers: headers });





        let a=1;
         let body = { "email": email,"motDePasse": motDePasse } ;
        //let body = "email="+email+"&motDePasse="+motDePasse;
        console.log("aaa");
        console.log(body);
        console.log("eee");
        return this.http.post('http://localhost:8070/utilisateurs/login',body ,options)
            .map( (response: Response) => {
                console.log('cccc');
                // login successful if there's a jwt token in the response
                //    if (1)
                //  return true;
                console.log(response);
                console.log("hhaaaaaaaaaaaaa");
                let rList: Role[];
                let token = response.json().email ;
                let  id=response.json().id;
                rList=response.json().roles;
                let nom = response.json().nom;
                let prenom= response.json().prenom;

                console.log(response.json());
                console.log(rList);
                console.log(token);
                //let activer=response.json().activer;
                if (token) {
                    // set token property
                    this.token = "logged";
                    console.log(this.token);
                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ email: email ,token: token ,nom : nom, prenom : prenom ,id: id , roles : rList }));
                    console.log(localStorage);
                    console.log("okk");
                    // return true to indicate successful login
                    return rList;
                } else {
                    // return false to indicate failed login
                    return rList;
                }
            });



    }




    logout(id): Observable<boolean> {
         let headers = new Headers();
        console.log('aaa');
       // headers.append('Authorization','Basic '+ btoa(email + ':' + motDePasse) );

        console.log(headers);

        console.log('role');




        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");

//
        headers.append("contentType","application/json");

        let options = new RequestOptions({ headers: headers });

        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');

         return this.http.post('http://localhost:8070/utilisateurs/logout/'+id,options)
      .map((res: Response) => res.json());
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
