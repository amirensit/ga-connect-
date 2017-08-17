import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { RefPack } from './ref-pack.model';
import { RefPackService } from './ref-pack.service';

@Component({
    selector: 'jhi-ref-pack-detail',
    templateUrl: './ref-pack-detail.component.html'
})
export class RefPackDetailComponent implements OnInit, OnDestroy {

    refPack: RefPack;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private refPackService: RefPackService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRefPacks();
    }

    load(id) {
        this.refPackService.find(id).subscribe((refPack) => {
            this.refPack = refPack;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRefPacks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'refPackListModification',
            (response) => this.load(this.refPack.id)
        );
    }
}
