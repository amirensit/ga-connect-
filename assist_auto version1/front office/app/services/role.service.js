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
var rolesService = (function () {
    function rolesService(http) {
        this.http = http;
        console.log("hhhhhhhh");
    }
    rolesService.prototype.getRoles = function () {
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
        return this.http.get("http://localhost:8070/roles", options)
            .map(function (res) { return res.json(); }, console.log("sandouda"));
    };
    rolesService.prototype.serviceFindRole = function (a, b, c) {
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
        return this.http.get('http://localhost:8070/roles/chercherRoles?mc='
            + a + "&page=" + b + "&size=" + c, options).map(function (res) { return res.json(); });
    };
    rolesService.prototype.getRole = function (id) {
        console.log("cc");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get('http://localhost:8070/roles/' + id, options)
            .map(function (res) { return res.json(); });
    };
    rolesService.prototype.modifierRole = function (id, libelle, mList) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "id": id, "libelle": libelle, "modules": mList };
        return this.http.put('http://localhost:8070/roles/' + id, body, options).map(function (res) { return res.json(); });
    };
    rolesService.prototype.deleteRole = function (id) {
        console.log("okk");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin,session-variable, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        console.log("ccgggggc");
        return this.http.delete('http://localhost:8070/roles/' + id, options)
            .map((function (response) { return response.json(); }), console.log("bye"));
    };
    rolesService.prototype.createRole = function (libelle, mList) {
        console.log("aaaaaaa");
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        //console.log(body);
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { libelle: libelle, "modules": mList };
        return this.http.post('http://localhost:8070/roles', body, options)
            .map(function (res) { return res.json(); });
    };
    rolesService.prototype.testIfRoleExist = function (libelle) {
        var body = "libelle=" + libelle;
        return this.http.get('http://localhost:8070/roles/role?' + body)
            .map(function (res) { return res.json(); });
    };
    rolesService.prototype.getModules = function () {
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
        return this.http.get("http://localhost:8070/modules", options)
            .map(function (res) { return res.json(); }, console.log("sandouda"));
    };
    rolesService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], rolesService);
    return rolesService;
}());
exports.rolesService = rolesService;
//# sourceMappingURL=role.service.js.map