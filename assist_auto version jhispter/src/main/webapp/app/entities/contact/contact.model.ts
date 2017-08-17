import { BaseEntity } from './../../shared';
import {Personne} from '../personne'

export class Contact implements BaseEntity {
    constructor(
        public id?: number,
        public isGerant?: boolean,
        public personne?: Personne,
        public remorqueurs?: BaseEntity[],
    ) {
        this.isGerant = false;
    }
}
