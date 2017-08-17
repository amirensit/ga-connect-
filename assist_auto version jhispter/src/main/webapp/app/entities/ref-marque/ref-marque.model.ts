import { BaseEntity } from './../../shared';

export class RefMarque implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
    ) {
    }
}
