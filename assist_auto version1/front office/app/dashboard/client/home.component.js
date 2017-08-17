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
var client_service_1 = require('../../services/client.service');
var router_1 = require("@angular/router");
var client_1 = require('../../model/client');
var angular2_flash_messages_1 = require('angular2-flash-messages');
var HomeComponent = (function () {
    function HomeComponent(_flashMessagesService, clientService, route) {
        this._flashMessagesService = _flashMessagesService;
        this.clientService = clientService;
        this.route = route;
        this.pListPackForAjoutClient = [];
        this.filtre = new client_1.ClientModel();
    }
    HomeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.clientService.getAllClient()
            .subscribe(function (posts) {
            _this.clients = posts;
        });
        this.clientService.getAllPack().subscribe(function (posts) { _this.pAjout = posts; });
        this.partieAffiche = true;
        this.partieAjout = false;
        //this._flashMessagesService.show('suppression validé !!!!', {cssClass: 'alert-success', timeout: 7000 });       
    };
    HomeComponent.prototype.afficherPartieListe = function () {
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    HomeComponent.prototype.afficherPartieAjoutClient = function () {
        this.partieAffiche = false;
        this.partieAjout = true;
    };
    HomeComponent.prototype.recupererIdClient = function (a) {
        this.n = a;
    };
    HomeComponent.prototype.validerSupprimerClient = function (b) {
        this.clientService.supprimerClient(b).subscribe(function (posts) {
        });
        window.location.reload();
        //this._flashMessagesService.show('suppression validé !!!!', {cssClass: 'alert-success', timeout: 7000 }); 
    };
    HomeComponent.prototype.editClient = function (a) {
        var _this = this;
        this.recupererIdClient(a);
        this.clientService.getClient(a)
            .subscribe(function (posts) {
            _this.num_client = posts.num_client;
            _this.nom_client = posts.nom_client;
            _this.etat = posts.etat;
            _this.p = posts.packs;
        });
    };
    HomeComponent.prototype.validerEditClient = function () {
        this.p = this.pListPackForAjoutClient;
        this.clientService.modifierClient(this.n, this.num_client, this.nom_client, this.etat, this.p)
            .subscribe(function (posts) {
        });
        window.location.reload();
    };
    HomeComponent.prototype.ajoutPackToClient = function (p, isChecked) {
        if (isChecked) {
            this.pListPackForAjoutClient.push(p);
        }
        else {
            var index = this.pListPackForAjoutClient.findIndex(function (x) { return x.id_pack == p.id_pack; });
            this.pListPackForAjoutClient.splice(index, 1);
        }
        console.log(this.pListPackForAjoutClient);
    };
    HomeComponent.prototype.ajouterClient = function () {
        this.etatForAjout = true;
        this.clientService.ajouterClient(this.num_clientForAjout, this.nom_clientForAjout, this.etatForAjout, this.pListPackForAjoutClient).subscribe(function (posts) {
        });
    };
    HomeComponent.prototype.bloquerClient = function (isChecked) {
        this.bloquerOk = true;
        this.debloquerOk = false;
        if (isChecked) {
            this.etat = false;
            this.etatForAjout = false;
        }
        else {
            this.etat = true;
            this.etatForAjout = true;
        }
        console.log(this.etat);
    };
    HomeComponent.prototype.debloquerClient = function (isChecked) {
        this.bloquerOk = false;
        this.debloquerOk = true;
        if (isChecked) {
            this.etat = true;
        }
        else {
            this.etat = false;
        }
        console.log(this.etat);
    };
    HomeComponent.prototype.verifierClientParNum = function () {
        var _this = this;
        this.clientService.verifierNumClient(this.num_clientForAjout).subscribe(function (posts) {
            _this.verifierNumClient = posts;
        });
        console.log(this.verifierNumClient);
    };
    HomeComponent = __decorate([
        core_1.Component({
            selector: 'home-cmp',
            moduleId: module.id,
            templateUrl: 'home.component.html',
            providers: [client_service_1.ClientService],
        }), 
        __metadata('design:paramtypes', [angular2_flash_messages_1.FlashMessagesService, client_service_1.ClientService, router_1.Router])
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
//# sourceMappingURL=home.component.js.map