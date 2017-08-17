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
var utilisateursService = (function () {
    function utilisateursService(http) {
        this.http = http;
        console.log("hhhhhhhh");
    }
    utilisateursService.prototype.getUtilisateurs = function () {
        console.log("hi");
        //let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        //let login=  currentUser.login;
        //let password= currentUser.password;
        var headers = new http_1.Headers();
        // headers.append('Authorization','Basic '+ btoa(login + ':' + password) );
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get("http://localhost:8070/utilisateurs", options)
            .map(function (res) { return res.json(); }, console.log("sandouda"));
    };
    utilisateursService.prototype.serviceFindUtilisateur = function (a, b, c) {
        // let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        // let login=  currentUser.login;
        //let password= currentUser.password;
        var headers = new http_1.Headers();
        // headers.append('Authorization','Basic '+ btoa(login + ':' + password) );
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get('http://localhost:8070/utilisateurs/chercherUtilisateurs?mc='
            + a + "&page=" + b + "&size=" + c, options).map(function (res) { return res.json(); });
    };
    utilisateursService.prototype.getUtilisateur = function (id) {
        console.log("cc");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get('http://localhost:8070/utilisateurs/' + id, options)
            .map(function (res) { return res.json(); });
    };
    utilisateursService.prototype.deleteUtilisateur = function (id) {
        console.log("okk");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin,session-variable, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        console.log("ccgggggc");
        return this.http.delete('http://localhost:8070/utilisateurs/' + id, options)
            .map((function (response) { return true; }), console.log("bye"));
    };
    utilisateursService.prototype.debloquerUtilisateur = function (id) {
        console.log("aaaaaaa");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.put('http://localhost:8070/utilisateurs/debloquer/' + id, options)
            .map(function (res) { return res.json(); }, console.log("bye"));
    };
    utilisateursService.prototype.bloquerUtilisateur = function (id) {
        console.log("aaaaaaa");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.put('http://localhost:8070/utilisateurs/bloquer/' + id, options)
            .map(function (res) { return res.json(); }, console.log("bye"));
    };
    utilisateursService.prototype.testIfUtilisateurExist = function (email) {
        var body = "email=" + email;
        return this.http.get('http://localhost:8070/utilisateurs/utilisateur?' + body)
            .map(function (res) { return res.json(); });
    };
    utilisateursService.prototype.testMotDePasse = function (motDePasse, email) {
        var body = "motDePasse=" + motDePasse + "&email=" + email;
        ;
        return this.http.get('http://localhost:8070/utilisateurs/utilisateur/password?' + body)
            .map(function (res) { return res.json(); });
    };
    utilisateursService.prototype.createUtilisateur = function (nom, prenom, email, motDePasse, numFixe, numMobile, status, blocage, connect, rList) {
        console.log("aaaaaaa");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        //console.log(body);
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { nom: nom, prenom: prenom, email: email, motDePasse: motDePasse, numFixe: numFixe, numMobile: numMobile, status: status,
            blocage: blocage, connect: connect, roles: rList };
        return this.http.post('http://localhost:8070/utilisateurs', body, options)
            .map(function (res) { return res.json(); });
    };
    utilisateursService.prototype.modifierUtilisateur = function (id, nom, prenom, email, motDePasse, numFixe, numMobile, status, blocage, connect, rList) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "id": id, "nom": nom, "prenom": prenom, "email": email, "motDePasse": motDePasse, "numFixe": numFixe,
            "numMobile": numMobile, "status": status, "blocage": blocage, "connect": connect, "roles": rList };
        return this.http.put('http://localhost:8070/utilisateurs/' + id, body, options).map(function (res) { return res.json(); });
    };
    utilisateursService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], utilisateursService);
    return utilisateursService;
}());
exports.utilisateursService = utilisateursService;
//# sourceMappingURL=utilisateur.service.js.map