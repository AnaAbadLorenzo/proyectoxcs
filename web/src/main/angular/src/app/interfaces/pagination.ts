export interface Pagination<T> {
  listaBusquedas: T[];
  tamanhoTotal: number;
  numResultados: number;
  inicio: number;
}
