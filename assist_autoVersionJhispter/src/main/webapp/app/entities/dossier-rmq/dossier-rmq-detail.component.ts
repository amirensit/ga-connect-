import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { DossierRmq } from './dossier-rmq.model';
import { DossierRmqService } from './dossier-rmq.service';

@Component({
    selector: 'jhi-dossier-rmq-detail',
    templateUrl: './dossier-rmq-detail.component.html'
})
export class DossierRmqDetailComponent implements OnInit, OnDestroy {

    dossierRmq: DossierRmq;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dossierRmqService: DossierRmqService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDossierRmqs();
    }

    load(id) {
        this.dossierRmqService.find(id).subscribe((dossierRmq) => {
            this.dossierRmq = dossierRmq;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDossierRmqs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'dossierRmqListModification',
            (response) => this.load(this.dossierRmq.id)
        );
    }
}
