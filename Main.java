
class printJob
{
    String user;
    int pages;
    char priority;

    public printJob(int pages, String user, char priority) {
        this.pages = pages;
        this.user = user;
        this.priority = priority;
    }
}

class Nodo {
    Nodo siguiente;
    Nodo anterior;
    printJob job;

    public Nodo() {}

    public Nodo(printJob job, Nodo siguiente, Nodo anterior) {
        this.job = job;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
}


class printQueue{

    private Nodo cabezaH;
    private Nodo cabezaM;
    private Nodo cabezaL;

    public void enqueue(printJob job){
        Nodo nuevo = new Nodo();
        nuevo.job = job;
        if (job.priority == 'H') {
            if (cabezaH == null){
                cabezaH = nuevo;
            }
            else{
                Nodo actual = cabezaH;
                while (actual.siguiente != null){
                    actual = actual.siguiente;
                }
                nuevo.anterior = actual;
                actual.siguiente = nuevo;
            }
        }
        else if (job.priority == 'M') {
            if (cabezaM == null){
                cabezaM = nuevo;
            }
            else{
                Nodo actual = cabezaM;
                while (actual.siguiente != null){
                    actual = actual.siguiente;
                }
                nuevo.anterior = actual;
                actual.siguiente = nuevo;
            }
        }
        else if (job.priority == 'L') {
            if (cabezaL == null){
                cabezaL = nuevo;
            }
            else{
                Nodo actual = cabezaL;
                while (actual.siguiente != null){
                    actual = actual.siguiente;
                }
                nuevo.anterior = actual;
                actual.siguiente = nuevo;
            }
        }
    }

    public printJob dequeue() {
        if (cabezaH != null) {
            return dequeueH();
        } else if (cabezaM != null) {
            return dequeueM();
        } else if (cabezaL != null) {
            return dequeueL();
        }
        return null;
    }

    private printJob dequeueH() {
        printJob res = cabezaH.job;
        cabezaH = cabezaH.siguiente;
        if (cabezaH != null) {
            cabezaH.anterior = null;
        }
        return res;
    }

    private printJob dequeueM() {
        printJob res = cabezaM.job;
        cabezaM = cabezaM.siguiente;
        if (cabezaM != null) {
            cabezaM.anterior = null;
        }
        return res;
    }

    private printJob dequeueL() {
        printJob res = cabezaL.job;
        cabezaL = cabezaL.siguiente;
        if (cabezaL != null) {
            cabezaL.anterior = null;
        }
        return res;
    }

    public boolean isempty() {
        return cabezaH == null && cabezaM == null && cabezaL == null;
    }

}

class printService {
    private printQueue cola = new printQueue();

    public void submitJob(printJob job) {
        cola.enqueue(job);
    }

    public void processAll() {
        while (!cola.isempty()) {
            printJob job = cola.dequeue();
            if (job != null) {
                System.out.println("Imprimiendo: " + job.user + " (" + job.pages +" páginas, prioridad " + job.priority + ")");
            }
        }
    }
}

class printManager {
    public static void main(String[] args) {
        printService servicio = new printService();
        servicio.submitJob(new printJob(5, "Juan", 'M'));
        servicio.submitJob(new printJob(2, "Ana", 'H'));
        servicio.submitJob(new printJob(10, "Luis", 'L'));
        servicio.submitJob(new printJob(3, "Carlos", 'H'));
        servicio.submitJob(new printJob(4, "Maria", 'M'));

        System.out.println("Orden de Impresión Esperado");
        servicio.processAll();
    }
}



