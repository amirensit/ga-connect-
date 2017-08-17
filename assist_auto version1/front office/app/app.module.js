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
var platform_browser_1 = require('@angular/platform-browser');
var router_1 = require('@angular/router');
var http_1 = require('@angular/http');
var ng2_validation_1 = require('ng2-validation');
var app_component_1 = require('./app.component');
var dashboard_component_1 = require('./dashboard/dashboard.component');
var dashboard_module_1 = require('./dashboard/dashboard.module');
var sidebar_module_1 = require('./sidebar/sidebar.module');
var footer_module_1 = require('./shared/footer/footer.module');
var navbar_module_1 = require('./shared/navbar/navbar.module');
var common_1 = require('@angular/common');
var forms_1 = require("@angular/forms");
var angular2_datatable_1 = require("angular2-datatable");
var client_service_1 = require("./services/client.service");
var contrat_service_1 = require("./services/contrat.service");
var remorqueur_service_1 = require("./services/remorqueur.service");
var pack_service_1 = require("./services/pack.service");
var marque_service_1 = require("./services/marque.service");
var typeService_service_1 = require("./services/typeService.service");
var gouvernorat_service_1 = require("./services/gouvernorat.service");
var ville_service_1 = require("./services/ville.service");
var ng2_filter_pipe_1 = require('ng2-filter-pipe');
var angular2_flash_messages_1 = require('angular2-flash-messages');
var role_service_1 = require("./services/role.service");
var utilisateur_service_1 = require("./services/utilisateur.service");
var authentication_service_1 = require("./services/authentication.service");
var auth_guard_1 = require("./guards/auth.guard");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                common_1.CommonModule,
                http_1.HttpModule,
                forms_1.FormsModule,
                angular2_flash_messages_1.FlashMessagesModule,
                forms_1.ReactiveFormsModule,
                platform_browser_1.BrowserModule,
                dashboard_module_1.DashboardModule,
                sidebar_module_1.SidebarModule,
                navbar_module_1.NavbarModule,
                footer_module_1.FooterModule,
                ng2_validation_1.CustomFormsModule,
                ng2_filter_pipe_1.Ng2FilterPipeModule,
                angular2_datatable_1.DataTableModule,
                router_1.RouterModule.forRoot([])
            ],
            declarations: [app_component_1.AppComponent, dashboard_component_1.DashboardComponent],
            providers: [{ provide: common_1.LocationStrategy, useClass: common_1.HashLocationStrategy }, authentication_service_1.AuthenticationService, contrat_service_1.ContratService, utilisateur_service_1.utilisateursService, role_service_1.rolesService, auth_guard_1.AuthGuard, client_service_1.ClientService, pack_service_1.PackService, marque_service_1.MarqueService, typeService_service_1.TypeServiceService, gouvernorat_service_1.GouvernoratService, ville_service_1.VilleService, remorqueur_service_1.RemorqueurService],
            bootstrap: [app_component_1.AppComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map