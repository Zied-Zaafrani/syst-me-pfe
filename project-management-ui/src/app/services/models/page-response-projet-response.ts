/* tslint:disable */
/* eslint-disable */
import { ProjetResponse } from '../models/projet-response';
export interface PageResponseProjetResponse {
  content?: Array<ProjetResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
