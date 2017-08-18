import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { RefTypeService } from './ref-type-service.model';
import { RefTypeServiceService } from './ref-type-service.service';

@Component({
    selector: 'jhi-ref-type-service-detail',
    templateUrl: './ref-type-service-detail.component.html'
})
export class RefTypeServiceDetailComponent implements OnInit, OnDestroy {

    refTypeService: RefTypeService;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private refTypeServiceService: RefTypeServiceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRefTypeServices();
    }

    load(id) {
        this.refTypeServiceService.find(id).subscribe((refTypeService) => {
            this.refTypeService = refTypeService;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRefTypeServices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'refTypeServiceListModification',
            (response) => this.load(this.refTypeService.id)
        );
    }
}
