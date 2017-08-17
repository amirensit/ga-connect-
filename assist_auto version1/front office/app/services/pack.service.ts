import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class PackService {

    constructor(private http: Http) {console.log("pack service initialisé !")}

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


getPack( id_pack : number)
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
         return this.http.get('http://localhost:8070/packs/'+id_pack,options).map(res=>res.json());
    }



modifierPack(id_pack : number , nom_pack :string , description : string , nbre_max_service : number , kilometrage_max : number,etat : boolean  )
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

        let body ={ "nom_pack" : nom_pack, "description" : description, "nbre_max_service" : nbre_max_service ,"kilometrage_max" :kilometrage_max ,"etat" : etat };
        return this.http.put('http://localhost:8070/packs/edit/'+id_pack,body,options) .map(res=>res.json());
    }

    supprimerPack(id  : number)
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

        return this.http.delete("http://localhost:8070/packs/delete/"+id,options).map( (res: Response) => res.json());

    }



    ajouterPack(nom_pack :string , description : string , nbre_max_service : number , kilometrage_max : number,etat : boolean )
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
        let body ={ "nom_pack" : nom_pack, "description" : description, "nbre_max_service" : nbre_max_service ,"kilometrage_max" :kilometrage_max ,"etat" : etat };
        return this.http.post("http://localhost:8070/packs/add",body,options).map(res=>res.json());

    }


}