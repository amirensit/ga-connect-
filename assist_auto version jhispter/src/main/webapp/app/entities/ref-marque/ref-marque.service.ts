import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { RefMarque } from './ref-marque.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RefMarqueService {

    private resourceUrl = 'api/ref-marques';
    private resourceSearchUrl = 'api/_search/ref-marques';

    constructor(private http: Http) { }

    create(refMarque: RefMarque): Observable<RefMarque> {
        const copy = this.convert(refMarque);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(refMarque: RefMarque): Observable<RefMarque> {
        const copy = this.convert(refMarque);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<RefMarque> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
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
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(refMarque: RefMarque): RefMarque {
        const copy: RefMarque = Object.assign({}, refMarque);
        return copy;
    }
}
