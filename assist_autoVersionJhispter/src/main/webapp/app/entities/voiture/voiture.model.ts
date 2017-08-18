import { BaseEntity } from './../../shared';
import {RefMarque} from '../ref-marque'
export class Voiture implements BaseEntity {
    constructor(
        public id?: number,
        public immatriculation?: string,
        public miseCirculation?: any,
        public puissanceFiscale?: number,
        public marque?: RefMarque,
        public contrat?: BaseEntity,
    ) {
    }
}
