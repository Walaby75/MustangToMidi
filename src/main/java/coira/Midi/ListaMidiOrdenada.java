package coira.Midi;

import java.util.*;
import coira.guitarra.Cuerda;

/**
 * Representa una lista de órdenes MIDI ordenadas automáticamente por el campo 'orden'.
 * Ideal para agrupar los pasos que conforman un evento o pulsada interpretada.
 * 
 * Cada vez que se agrega una OrdenMidi2025, la lista se reordena automáticamente.
 */
public class ListaMidiOrdenada extends ArrayList<OrdenMidi2025> {
    private boolean autoSort = true;

    public ListaMidiOrdenada() {}

    public ListaMidiOrdenada(boolean autoSort) {
        this.autoSort = autoSort;
    }
    
    
    @Override
    public boolean add(OrdenMidi2025 step) {
        boolean changed = super.add(step);
        if (autoSort) this.sort(Comparator.naturalOrder());
        return changed;
    }

    @Override
    public boolean addAll(Collection<? extends OrdenMidi2025> c) {
        boolean changed = super.addAll(c);
        if (changed) this.sort(Comparator.naturalOrder());
        return changed;
    }

    public void ordenar() {
        this.sort(Comparator.naturalOrder());
    }
    
    /**
     * Agrega una nueva orden directamente con sus parámetros básicos.
     */
    public void add(int orden, int comando, int nota, int fuerza, int delay, Integer canal, String puerto, Cuerda cuerda) {
        OrdenMidi2025 step = new OrdenMidi2025();
        step.setOrden(orden);
        step.setComando(comando);
        step.setNota(nota);
        step.setFuerza(fuerza);
        step.setDelay(delay);
        step.setCanal(canal);
        step.setPuerto(puerto);
        step.setCuerda(cuerda);
        add(step);
    }

    /**
     * Devuelve una lista inmutable ordenada (ideal para recorrerla desde un hilo de salida).
     */
    public List<OrdenMidi2025> getOrderedView() {
        return Collections.unmodifiableList(this);
    }

    /**
     * Reordena manualmente (por si se agregaron elementos sin usar add/addAll).
     */
    public void sortSteps() {
        this.sort(Comparator.naturalOrder());
    }

    /**
     * Fusiona otra lista en esta (útil para combinar pasos de superclase e hija).
     * El resultado queda automáticamente ordenado.
     */
    public void merge(ListaMidiOrdenada otra) {
        if (otra == null || otra.isEmpty()) return;
        super.addAll(otra);
        this.sort(Comparator.naturalOrder());
    }

    /**
     * Crea una copia profunda de esta lista (útil para procesamiento en otro hilo).
     */
    public ListaMidiOrdenada copy() {
        ListaMidiOrdenada clone = new ListaMidiOrdenada();
        for (OrdenMidi2025 step : this) {
            OrdenMidi2025 s = new OrdenMidi2025();
            s.setOrden(step.getOrden());
            s.setCuerda(step.getCuerda());
            s.setPuerto(step.getPuerto());
            s.setCanal(step.getCanal());
            s.setComando(step.getComando());
            s.setNota(step.getNota());
            s.setFuerza(step.getFuerza());
            s.setDelay(step.getDelay());
            clone.add(s);
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListaMidiOrdenada[\n");
        for (OrdenMidi2025 o : this) {
            sb.append(String.format(
                "  orden=%d, comando=%d, nota=%d, fuerza=%d, delay=%d, canal=%s, puerto=%s, cuerda=%s\n",
                o.getOrden(), o.getComando(), o.getNota(), o.getFuerza(), o.getDelay(),
                o.getCanal(), o.getPuerto(), 
                o.getCuerda() != null ? o.getCuerda().toString() : "null"
            ));
        }
        sb.append("]");
        return sb.toString();
    }
}
