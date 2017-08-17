"use strict";
var home_component_1 = require('./client/home.component');
var pack_component_1 = require('./pack/pack.component');
var marque_component_1 = require('./marque/marque.component');
var typeService_component_1 = require('./typeService/typeService.component');
var remorqueur_component_1 = require('./remorqueur/remorqueur.component');
var home_component_2 = require('./utilisateur/home.component');
var role_component_1 = require('./role/role.component');
var contrat_component_1 = require('./contrat/contrat.component');
var login_component_1 = require("../login/login.component");
var auth_guard_1 = require("../guards/auth.guard");
exports.MODULE_ROUTES = [
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'roles', component: role_component_1.RoleComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'utilisateurs', component: home_component_2.UtilisateurComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'clients', component: home_component_1.HomeComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'packs', component: pack_component_1.PackComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'marques', component: marque_component_1.MarqueComponent, canActivate: [auth_guard_1.AuthGuard] },
    // { path: 'gouvernorats', component: GouvernoratComponent },
    // { path: 'villes', component: VilleComponent },
    { path: 'typeService', component: typeService_component_1.TypeServiceComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'remorqueurs', component: remorqueur_component_1.RemorqueurComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'contrats', component: contrat_component_1.ContratComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: '', redirectTo: 'login', pathMatch: 'full', canActivate: [auth_guard_1.AuthGuard] }
];
exports.MODULE_COMPONENTS = [
    login_component_1.LoginComponent,
    role_component_1.RoleComponent,
    home_component_2.UtilisateurComponent,
    home_component_1.HomeComponent,
    //HelloComponent,
    pack_component_1.PackComponent,
    marque_component_1.MarqueComponent,
    //GouvernoratComponent,
    //VilleComponent,
    typeService_component_1.TypeServiceComponent,
    remorqueur_component_1.RemorqueurComponent,
    contrat_component_1.ContratComponent
];
//# sourceMappingURL=dashboard.routes.js.map