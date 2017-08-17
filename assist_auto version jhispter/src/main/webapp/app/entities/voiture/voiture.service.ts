import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { Voiture } from './voiture.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VoitureService {

    private resourceUrl = 'api/voitures';
    private resourceSearchUrl = 'api/_search/voitures';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(voiture: Voiture): Observable<Voiture> {
        const copy = this.convert(voiture);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(voiture: Voiture): Observable<Voiture> {
        const copy = this.convert(voiture);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Voiture> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.miseCirculation = this.dateUtils
            .convertLocalDateFromServer(entity.miseCirculation);
    }

    private convert(voiture: Voiture): Voiture {
        const copy: Voiture = Object.assign({}, voiture);
        copy.miseCirculation = this.dateUtils
            .convertLocalDateToServer(voiture.miseCirculation);
        return copy;
    }
}
