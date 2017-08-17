/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RefMarqueDetailComponent } from '../../../../../../main/webapp/app/entities/ref-marque/ref-marque-detail.component';
import { RefMarqueService } from '../../../../../../main/webapp/app/entities/ref-marque/ref-marque.service';
import { RefMarque } from '../../../../../../main/webapp/app/entities/ref-marque/ref-marque.model';

describe('Component Tests', () => {

    describe('RefMarque Management Detail Component', () => {
        let comp: RefMarqueDetailComponent;
        let fixture: ComponentFixture<RefMarqueDetailComponent>;
        let service: RefMarqueService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [RefMarqueDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RefMarqueService,
                    JhiEventManager
                ]
            }).overrideTemplate(RefMarqueDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RefMarqueDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefMarqueService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RefMarque(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.refMarque).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
