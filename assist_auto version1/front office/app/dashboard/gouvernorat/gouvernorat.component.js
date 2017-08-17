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
var router_1 = require("@angular/router");
var gouvernorat_service_1 = require("../../services/gouvernorat.service");
var GouvernoratComponent = (function () {
    function GouvernoratComponent(gouvernoratService, route) {
        this.gouvernoratService = gouvernoratService;
        this.route = route;
    }
    GouvernoratComponent.prototype.ajouterGouvernorat = function () {
        this.gouvernoratService.ajouterGouvernorat(this.nom_gouvernorat)
            .subscribe(function (posts) {
        });
    };
    GouvernoratComponent = __decorate([
        core_1.Component({
            selector: 'icons-cmp',
            moduleId: module.id,
            templateUrl: 'gouvernorat.component.html',
            providers: [gouvernorat_service_1.GouvernoratService],
        }), 
        __metadata('design:paramtypes', [gouvernorat_service_1.GouvernoratService, router_1.Router])
    ], GouvernoratComponent);
    return GouvernoratComponent;
}());
exports.GouvernoratComponent = GouvernoratComponent;
//# sourceMappingURL=gouvernorat.component.js.map