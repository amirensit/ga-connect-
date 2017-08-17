import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { RefTypeService } from './ref-type-service.model';
import { RefTypeServiceService } from './ref-type-service.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-ref-type-service',
    templateUrl: './ref-type-service.component.html'
})
export class RefTypeServiceComponent implements OnInit, OnDestroy {
refTypeServices: RefTypeService[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private refTypeServiceService: RefTypeServiceService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.refTypeServiceService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.refTypeServices = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.refTypeServiceService.query().subscribe(
            (res: ResponseWrapper) => {
                this.refTypeServices = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRefTypeServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RefTypeService) {
        return item.id;
    }
    registerChangeInRefTypeServices() {
        this.eventSubscriber = this.eventManager.subscribe('refTypeServiceListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
