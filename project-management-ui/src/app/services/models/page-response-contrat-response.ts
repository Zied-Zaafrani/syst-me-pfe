/* tslint:disable */
/* eslint-disable */
import { ContratResponse } from '../models/contrat-response';
export interface PageResponseContratResponse {
  content?: Array<ContratResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
