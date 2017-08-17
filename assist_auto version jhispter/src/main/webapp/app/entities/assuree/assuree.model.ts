import { BaseEntity } from './../../shared';
import {Personne} from '../personne';
import {Contrat} from '../contrat'
export class Assuree implements BaseEntity {
    constructor(
        public id?: number,
        public personne?: Personne,
        public contrats?: Contrat[],
    ) {
    }
}
