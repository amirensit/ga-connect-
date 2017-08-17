import { BaseEntity } from './../../shared';
import {Voiture} from '../voiture';
import {Assuree} from '../assuree';
import {RefPack} from '../ref-pack';
import {RefCompagnie} from '../ref-compagnie';
 export class Contrat implements BaseEntity {
    constructor(
        public id?: number,
        public numero?: number,
        public debut?: any,
        public fin?: any,
        public voiture?: Voiture,
        public assure?: Assuree,
        public pack?: RefPack,
        public compagnie?: RefCompagnie,
    ) {
    }
}
