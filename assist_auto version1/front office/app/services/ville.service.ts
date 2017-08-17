import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class VilleService {

    constructor(private http: Http) {console.log("client service initialisé !")}


    ajouterVille( nom_ville: string ,id_gouvernorat : number )
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
        let body ={"nom_ville": nom_ville ,"gouvernoratDTO" :{"id_gouvernorat" : id_gouvernorat}};
        return this.http.post("http://localhost:8070/villes/add",body,options).map(res=>res.json());

    }

    getVilleByGouvernorat(id_gouvernorat : number)
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
         return this.http.get('http://localhost:8070/villes/getIdVilleByGouvernorat/'+id_gouvernorat,options).map(res=>res.json());
    }

   

}