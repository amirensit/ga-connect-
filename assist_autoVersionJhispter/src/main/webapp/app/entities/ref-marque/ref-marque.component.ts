import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { RefMarque } from './ref-marque.model';
import { RefMarqueService } from './ref-marque.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-ref-marque',
    templateUrl: './ref-marque.component.html'
})
export class RefMarqueComponent implements OnInit, OnDestroy {
refMarques: RefMarque[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private refMarqueService: RefMarqueService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.refMarqueService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.refMarques = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.refMarqueService.query().subscribe(
            (res: ResponseWrapper) => {
                this.refMarques = res.json;
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
        this.registerChangeInRefMarques();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RefMarque) {
        return item.id;
    }
    registerChangeInRefMarques() {
        this.eventSubscriber = this.eventManager.subscribe('refMarqueListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
