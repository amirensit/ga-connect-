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
var GouvernoratService = (function () {
    function GouvernoratService(http) {
        this.http = http;
        console.log("client service initialisé !");
    }
    GouvernoratService.prototype.ajouterGouvernorat = function (nom_gouvernorat) {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        var body = { "nom_gouvernorat": nom_gouvernorat };
        return this.http.post("http://localhost:8070/gouvernorats/add", body, options).map(function (res) { return res.json(); });
    };
    GouvernoratService.prototype.getListGouvernorat = function () {
        var headers = new http_1.Headers();
        headers.append("Access-Control-Allow-Origin", "*");
        headers.append("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.append("Access-Control-Allow-Credentials", "true");
        headers.append("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.append("contentType", "*");
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get("http://localhost:8070/gouvernorats/getListGouvernorat", options).map(function (res) { return res.json(); });
    };
    GouvernoratService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], GouvernoratService);
    return GouvernoratService;
}());
exports.GouvernoratService = GouvernoratService;
//# sourceMappingURL=gouvernorat.service.js.map