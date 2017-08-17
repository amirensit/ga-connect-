import {Injectable} from "@angular/core";
import {Http,Response,RequestOptions,Headers,RequestMethod} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';



@Injectable()
export class ContratService {

    constructor(private http: Http) {}

    getAllContrat(){
      
      
        let headers = new Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        headers.append("contentType","*");
        let options = new RequestOptions( {headers: headers });
        return this.http.get(`http://localhost:8070/contrats/all`,options)
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






    
    getContrat( id_contrat)
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
         return this.http.get('http://localhost:8070/contrats/'+id_contrat,options).map(res=>res.json());
    }
    
    modifierContrat(id_contrat,addresse,date_debut,date_fin,nom,num_contrat,prenom,id_villeForEdit,id_marqueForEdit,id_packForEdit )
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

        let body ={"addresse" : addresse 
        , "date_debut" : date_debut 
        , "date_fin"  : date_fin 
        ,"nom" : nom ,"num_contrat" : num_contrat 
        , "prenom" : prenom
        , "villeDTO" : {"id_ville" :id_villeForEdit }
        ,"packDTO" :{"id_pack" :id_packForEdit  }
        , "marqueDTO" : {"id_marque" : id_marqueForEdit}  };
        return this.http.put('http://localhost:8070/contrats/edit/'+id_contrat,body,options) .map(res=>res.json());
    }


    ajouterContrat( addresse,date_debut,date_fin,nom
    ,num_contrat,prenom,id_villeForAjout,id_marqueForAjout
    ,id_packForAjout   )
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
        let body ={"addresse" : addresse 
        , "date_debut" : date_debut 
        , "date_fin"  : date_fin 
        ,"nom" : nom ,"num_contrat" : num_contrat 
        , "prenom" : prenom
        , "villeDTO" : {"id_ville" :id_villeForAjout }
        ,"packDTO" :{"id_pack" :id_packForAjout  }
        , "marqueDTO" : {"id_marque" : id_marqueForAjout}  };
        return this.http.post("http://localhost:8070/contrats/add",body,options).map(res=>res.json());

    }


    

    importerContrat()
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
        let body = {}
        return this.http.post('http://localhost:8070/contrats/import',body,options).map(res=>res.json());

    }

     



}


