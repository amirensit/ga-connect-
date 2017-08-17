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
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
require('rxjs/add/operator/map');
var AuthenticationService = (function () {
    function AuthenticationService(http) {
        this.http = http;
        // set token if saved in local storage
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }
    AuthenticationService.prototype.login = function (email, motDePasse) {
        var _this = this;
        var headers = new http_1.Headers();
        console.log('aaa');
        headers.append('Authorization', 'Basic ' + btoa(email + ':' + motDePasse));
        console.log(headers);
        console.log('role');
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        //
        headers.append("contentType", "application/json");
        var options = new http_1.RequestOptions({ headers: headers });
        var a = 1;
        var body = { "email": email, "motDePasse": motDePasse };
        //let body = "email="+email+"&motDePasse="+motDePasse;
        console.log("aaa");
        console.log(body);
        console.log("eee");
        return this.http.post('http://localhost:8070/utilisateurs/login', body, options)
            .map(function (response) {
            console.log('cccc');
            // login successful if there's a jwt token in the response
            //    if (1)
            //  return true;
            console.log(response);
            console.log("hhaaaaaaaaaaaaa");
            var rList;
            var token = response.json().email;
            var id = response.json().id;
            rList = response.json().roles;
            var nom = response.json().nom;
            var prenom = response.json().prenom;
            console.log(response.json());
            console.log(rList);
            console.log(token);
            //let activer=response.json().activer;
            if (token) {
                // set token property
                _this.token = "logged";
                console.log(_this.token);
                // store username and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify({ email: email, token: token, nom: nom, prenom: prenom, id: id, roles: rList }));
                console.log(localStorage);
                console.log("okk");
                // return true to indicate successful login
                return rList;
            }
            else {
                // return false to indicate failed login
                return rList;
            }
        });
    };
    AuthenticationService.prototype.logout = function (id) {
        var headers = new http_1.Headers();
        console.log('aaa');
        // headers.append('Authorization','Basic '+ btoa(email + ':' + motDePasse) );
        console.log(headers);
        console.log('role');
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        //
        headers.append("contentType", "application/json");
        var options = new http_1.RequestOptions({ headers: headers });
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
        return this.http.post('http://localhost:8070/utilisateurs/logout/' + id, options)
            .map(function (res) { return res.json(); });
    };
    AuthenticationService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], AuthenticationService);
    return AuthenticationService;
}());
exports.AuthenticationService = AuthenticationService;
//# sourceMappingURL=authentication.service.js.map