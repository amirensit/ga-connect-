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
var MarqueService = (function () {
    function MarqueService(http) {
        this.http = http;
        console.log("client service initialisé !");
    }
    MarqueService.prototype.getAllMarque = function () {
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
    MarqueService.prototype.supprimerMarque = function (id) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.delete("http://localhost:8070/marques/delete/" + id, options).map(function (res) { return res.json(); });
    };
    MarqueService.prototype.getMarque = function (id_marque) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get('http://localhost:8070/marques/' + id_marque, options).map(function (res) { return res.json(); });
    };
    MarqueService.prototype.modifierMarque = function (id_marque, nom_marque) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "nom_marque": nom_marque };
        return this.http.put('http://localhost:8070/marques/edit/' + id_marque, body, options).map(function (res) { return res.json(); });
    };
    MarqueService.prototype.ajouterMarque = function (nom_marque) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "nom_marque": nom_marque };
        return this.http.post("http://localhost:8070/marques/add", body, options).map(function (res) { return res.json(); });
    };
    MarqueService.prototype.aaaa = function (a, b, c) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get("http://localhost:8070/marques/chercherMarques?mc=" + a + "&page=" + b + "&size=" + c, options).map(function (res) { return res.json(); });
    };
    MarqueService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], MarqueService);
    return MarqueService;
}());
exports.MarqueService = MarqueService;
//# sourceMappingURL=marque.service.js.map