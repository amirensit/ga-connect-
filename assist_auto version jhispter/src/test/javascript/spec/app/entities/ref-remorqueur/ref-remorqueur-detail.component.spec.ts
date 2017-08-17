/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RefRemorqueurDetailComponent } from '../../../../../../main/webapp/app/entities/ref-remorqueur/ref-remorqueur-detail.component';
import { RefRemorqueurService } from '../../../../../../main/webapp/app/entities/ref-remorqueur/ref-remorqueur.service';
import { RefRemorqueur } from '../../../../../../main/webapp/app/entities/ref-remorqueur/ref-remorqueur.model';

describe('Component Tests', () => {

    describe('RefRemorqueur Management Detail Component', () => {
        let comp: RefRemorqueurDetailComponent;
        let fixture: ComponentFixture<RefRemorqueurDetailComponent>;
        let service: RefRemorqueurService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [RefRemorqueurDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RefRemorqueurService,
                    JhiEventManager
                ]
            }).overrideTemplate(RefRemorqueurDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RefRemorqueurDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefRemorqueurService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RefRemorqueur(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.refRemorqueur).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
