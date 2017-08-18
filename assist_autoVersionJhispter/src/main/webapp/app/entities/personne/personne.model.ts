import { BaseEntity } from './../../shared';
import {SysVille} from '../sys-ville'
export class Personne implements BaseEntity {
    constructor(
        public id?: number,
        public prenom?: string,
        public nom?: string,
        public telPrincipal?: number,
        public autreTel?: number,
        public fax?: number,
        public mailPrincipal?: string,
        public autreMail?: string,
        public adresse?: string,
        public latitude?: string,
        public longitude?: string,
        public ville?: SysVille,
    ) {
    }

    
}
