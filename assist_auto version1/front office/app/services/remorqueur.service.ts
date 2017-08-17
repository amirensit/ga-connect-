import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class RemorqueurService {

    constructor(private http: Http) {console.log("client service initialisé !")}

    getAllRemorqueur(){
      
      
        let headers = new Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        headers.append("contentType","*");
        let options = new RequestOptions( {headers: headers });
        return this.http.get(`http://localhost:8070/remorqueurs/all`,options)
            .map( (res: Response) => res.json());
    }






    supprimerRemorqueur(id  : number)
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

        return this.http.delete("http://localhost:8070/remorqueurs/delete/"+id,options).map( (res: Response) => res.json());

    }

    getRemorqueur( id_remorqueur : number)
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
         return this.http.get('http://localhost:8070/remorqueurs/'+id_remorqueur,options).map(res=>res.json());
    }

                      
    modifierRemorqueur(id_remorqueur : number , matricule_fiscal :string , nom_contact : string , prenom_contact : string 
            ,etat : boolean,id_ville : number , raison_sociale : string , tel : string  )
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

    let body = {"raison_social" : raison_sociale , "matricule_fiscal" : matricule_fiscal , "tel" : tel, "nom_contact" : nom_contact , "prenom_contact" :prenom_contact , "villeDTO" :{"id_ville" : id_ville },"etat" :etat };
        return this.http.put('http://localhost:8070/remorqueurs/edit/'+id_remorqueur,body,options) .map(res=>res.json());
    }


    ajouterRemorqueur(matricule_fiscal :string , nom_contact : string 
    ,prenom_contact : string, etat : boolean
    ,id_ville : number , raison_sociale : string , tel : string )
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
        let body = {"raison_social" : raison_sociale , "matricule_fiscal" : matricule_fiscal , "tel" : tel, "nom_contact" : nom_contact , "prenom_contact" :prenom_contact , "villeDTO" :{"id_ville" : id_ville },"etat" :etat };
        return this.http.post("http://localhost:8070/remorqueurs/add",body,options).map(res=>res.json());

    }

     



}


