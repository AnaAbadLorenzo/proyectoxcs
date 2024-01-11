package es.uvigo.esei.dgss.teama.microstories.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the object Pagination that returns the results to the view
 *
 * @param <T> this the class object that contains the results
 */
public class Pagination<T> {

  private List<T> listaBusquedas = new ArrayList<T>();
  private int tamanhoTotal;
  private int numResultados;
  private int inicio;

  public Pagination() {
    super();
  }

  public Pagination(
      final List<T> listaBusquedas,
      final int tamanhoTotal,
      final int numResultados,
      final int inicio) {
    super();
    this.listaBusquedas = listaBusquedas;
    this.tamanhoTotal = tamanhoTotal;
    this.numResultados = numResultados;
    this.inicio = inicio;
  }

  public List<T> getListaBusquedas() {
    return listaBusquedas;
  }

  public int getTamanhoTotal() {
    return tamanhoTotal;
  }

  public int getNumResultados() {
    return numResultados;
  }

  public int getInicio() {
    return inicio;
  }
}
