"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require('rxjs/add/operator/map');
var ContratService = (function () {
    function ContratService(http) {
        this.http = http;
    }
    ContratService.prototype.getAllContrat = function () {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get("http://localhost:8070/contrats/all", options)
            .map(function (res) { return res.json(); });
    };
    ContratService.prototype.getAllPack = function () {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get("http://localhost:8070/packs/getListPack", options)
            .map(function (res) { return res.json(); });
    };
    ContratService.prototype.getAllMarque = function () {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get("http://localhost:8070/marques/getListMarque", options)
            .map(function (res) { return res.json(); });
    };
    ContratService.prototype.getContrat = function (id_contrat) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get('http://localhost:8070/contrats/' + id_contrat, options).map(function (res) { return res.json(); });
    };
    ContratService.prototype.modifierContrat = function (id_contrat, addresse, date_debut, date_fin, nom, num_contrat, prenom, id_villeForEdit, id_marqueForEdit, id_packForEdit) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "addresse": addresse,
            "date_debut": date_debut,
            "date_fin": date_fin,
            "nom": nom, "num_contrat": num_contrat,
            "prenom": prenom,
            "villeDTO": { "id_ville": id_villeForEdit },
            "packDTO": { "id_pack": id_packForEdit },
            "marqueDTO": { "id_marque": id_marqueForEdit } };
        return this.http.put('http://localhost:8070/contrats/edit/' + id_contrat, body, options).map(function (res) { return res.json(); });
    };
    ContratService.prototype.ajouterContrat = function (addresse, date_debut, date_fin, nom, num_contrat, prenom, id_villeForAjout, id_marqueForAjout, id_packForAjout) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "addresse": addresse,
            "date_debut": date_debut,
            "date_fin": date_fin,
            "nom": nom, "num_contrat": num_contrat,
            "prenom": prenom,
            "villeDTO": { "id_ville": id_villeForAjout },
            "packDTO": { "id_pack": id_packForAjout },
            "marqueDTO": { "id_marque": id_marqueForAjout } };
        return this.http.post("http://localhost:8070/contrats/add", body, options).map(function (res) { return res.json(); });
    };
    ContratService.prototype.importerContrat = function () {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = {};
        return this.http.post('http://localhost:8070/contrats/import', body, options).map(function (res) { return res.json(); });
    };
    ContratService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], ContratService);
    return ContratService;
}());
exports.ContratService = ContratService;
//# sourceMappingURL=contrat.service.js.map