import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import  'rxjs/add/operator/map';
import  'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import {Headers,RequestOptions} from '@angular/http';
import 'rxjs/add/observable/throw';
 


@Injectable ()
export class AuthService {

    public token: string;
    constructor(private http: Http) {
        console.log('GetssService constructor initialised');
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }




login(login: string, password: string)
{

        let headers = new Headers();
        headers.append('Authorization','Basic '+ btoa(login + ':' + password) );
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType","application/json");
        let options = new RequestOptions({ headers: headers });
        let body = { "login": login,"password": password } ;
        return this.http.post('http://localhost:8070/users/login', body ,options)
            .map(response => {
                let role=0;
                let token = response.json().login ;
                let  id=response.json().id;
                role=response.json().role.id;
                let firstName = response.json().firstName;
                let lastName= response.json().lastName;

                console.log(response.json());
                console.log(role);
                console.log(token);
                
                if (token) {
                 
                    this.token = "logged";
                    console.log(this.token);
                    
                    localStorage.setItem('currentUser', JSON.stringify({ login: login, password: password ,token: token ,firstName : firstName, lastName : lastName ,id: id , role : role }));
                    console.log(localStorage);
                    console.log("okk");
                    
                    return role;
                } else {
                    
                    return role;
                }
            });
}

logout()
{
    this.token=null;
    localStorage.removeItem('currentUser');
}




























}