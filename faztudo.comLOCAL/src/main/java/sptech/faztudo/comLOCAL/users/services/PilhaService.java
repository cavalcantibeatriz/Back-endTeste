package sptech.faztudo.comLOCAL.users.services;
import org.springframework.stereotype.Service;

@Service
public class PilhaService <T>{
    private T[] pilha;
    private int topo;

    public PilhaService() {
        pilha = (T[]) new Object[10];
        topo = -1;
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T info) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
            throw new IllegalStateException("Pilha cheia");
        } else {
            pilha[++topo] = info;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null; // Ou lançar uma exceção, se preferir
        }
        return pilha[topo--];
    }

    public T peek() {
        if (isEmpty()) {
            return null; // Ou lançar uma exceção, se preferir
        }
        return pilha[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        } else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }

    public int getTopo() {
        return topo;
    }
}
