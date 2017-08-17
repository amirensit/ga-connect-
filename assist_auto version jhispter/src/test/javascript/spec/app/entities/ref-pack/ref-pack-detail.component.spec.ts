/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RefPackDetailComponent } from '../../../../../../main/webapp/app/entities/ref-pack/ref-pack-detail.component';
import { RefPackService } from '../../../../../../main/webapp/app/entities/ref-pack/ref-pack.service';
import { RefPack } from '../../../../../../main/webapp/app/entities/ref-pack/ref-pack.model';

describe('Component Tests', () => {

    describe('RefPack Management Detail Component', () => {
        let comp: RefPackDetailComponent;
        let fixture: ComponentFixture<RefPackDetailComponent>;
        let service: RefPackService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [RefPackDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RefPackService,
                    JhiEventManager
                ]
            }).overrideTemplate(RefPackDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RefPackDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefPackService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RefPack(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.refPack).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
