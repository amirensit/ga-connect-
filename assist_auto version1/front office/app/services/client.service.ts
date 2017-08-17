import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class ClientService {

    constructor(private http: Http) {console.log("client service initialisé !")}

    getAllClient(){
      
      
        let headers = new Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        headers.append("contentType","*");
        let options = new RequestOptions( {headers: headers });
        return this.http.get(`http://localhost:8070/clients/all`,options)
            .map( (res: Response) => res.json());
    }

getAllPack()
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

         return this.http.get(`http://localhost:8070/packs/getListPack`,options)
            .map( (res: Response) => res.json());
}




    supprimerClient(id  : number)
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

        return this.http.delete("http://localhost:8070/clients/delete/"+id,options).map( (res: Response) => res.json());

    }

    getClient( id_client : number)
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
         return this.http.get('http://localhost:8070/clients/'+id_client,options).map(res=>res.json());
    }

    modifierClient(id_client : number , num_client :number , nom_client : string , etat : boolean , p : Pack[] )
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

        let body ={ "num_client" : num_client, "etat" : etat, "nom_client" : nom_client ,"packs" :p};
        return this.http.put('http://localhost:8070/clients/edit/'+id_client,body,options) .map(res=>res.json());
    }

    ajouterClient(num_client :number , nom_client : string , etat : boolean , pList : Pack[])
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
        let body ={ "num_client": num_client,"etat" : etat , "nom_client": nom_client,"packs" : pList };
        return this.http.post("http://localhost:8070/clients/add",body,options).map(res=>res.json());

    }

    verifierNumClient(num : number)
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
         return this.http.get('http://localhost:8070/clients/verifierDejaExist/'+num).map(res=>res.text());

    }

     



}


interface Pack{
    id_pack : number;
    nom_pack : string;
    description : string;
    nbre_max_service : number;
    kilometrage_max : number;
    etat : string;
}