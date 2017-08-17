import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class MarqueService {

    constructor(private http: Http) {console.log("client service initialisé !")}

    getAllMarque(){
      
      
        let headers = new Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        headers.append("contentType","*");
        let options = new RequestOptions( {headers: headers });
        return this.http.get(`http://localhost:8070/marques/getListMarque`,options)
            .map( (res: Response) => res.json());
    }


    supprimerMarque(id  : number)
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

        return this.http.delete("http://localhost:8070/marques/delete/"+id,options).map( (res: Response) => res.json());

    }

    getMarque( id_marque : number)
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
         return this.http.get('http://localhost:8070/marques/'+id_marque,options).map(res=>res.json());
    }

    modifierMarque(id_marque : number , nom_marque : string )
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

        let body ={ "nom_marque" : nom_marque};
        return this.http.put('http://localhost:8070/marques/edit/'+id_marque,body,options) .map(res=>res.json());
    }

    ajouterMarque( nom_marque: string  )
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
        let body ={ "nom_marque": nom_marque};
        return this.http.post("http://localhost:8070/marques/add",body,options).map(res=>res.json());

    }

    aaaa(a,b,c)
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
         return this.http.get("http://localhost:8070/marques/chercherMarques?mc="+a+"&page="+b+"&size="+c,options).map(res=>res.json());
    }

     



}

