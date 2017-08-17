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
var router_1 = require('@angular/router');
var authentication_service_1 = require("../services/authentication.service");
var utilisateur_service_1 = require("../services/utilisateur.service");
var LoginComponent = (function () {
    function LoginComponent(router, authenticationService, utilisateurService) {
        this.router = router;
        this.authenticationService = authenticationService;
        this.utilisateurService = utilisateurService;
        this.model = {};
        this.modal = {};
        this.loading = false;
        this.error = '';
        this.selectedrole = {};
        this.utilisateurExistError = '';
        this.passExistError = '';
    }
    LoginComponent.prototype.ngOnInit = function () {
        // reset login status
        //  this.logout();
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    };
    LoginComponent.prototype.login = function () {
        var _this = this;
        this.loading = true;
        this.authenticationService.login(this.model.email, this.model.motDePasse)
            .subscribe(function (result1) {
            if (result1 != null) {
                console.log(result1);
                _this.router.navigate(['/roles']);
                window.location.reload();
                console.log();
            }
            else {
                _this.error = 'Username or password is incorrect';
                _this.loading = false;
            }
        }, function (error) {
            //this.error = 'Username or password is incorrect';
            //console.log("errorrrr");
            _this.loading = false;
            //this.model.username='';
            //this.model.password='';
            //this.router.navigate(['login']);
            _this.utilisateurService.testIfUtilisateurExist(_this.model.email)
                .subscribe(function (res) {
                if (res == false) {
                    _this.t = true;
                    _this.h = false;
                    _this.loading = false;
                    console.log(_this.t);
                    _this.error = "Email incorrect";
                    console.log("mail");
                }
                else {
                    _this.utilisateurService.testMotDePasse(_this.model.motDePasse, _this.model.email)
                        .subscribe(function (r) {
                        if (r == false) {
                            _this.h = true;
                            _this.t = false;
                            console.log(_this.h);
                            _this.loading = false;
                            _this.passExistError = "mot de passe incorrecte";
                            console.log("pass");
                        }
                    });
                }
            });
        });
    };
    LoginComponent.prototype.logout = function (id) {
        var _this = this;
        this.authenticationService.logout(id).subscribe(function (result) {
            _this.model = result;
            console.log(_this.model);
        });
    };
    LoginComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'login.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.Router, authentication_service_1.AuthenticationService, utilisateur_service_1.utilisateursService])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map