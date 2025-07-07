/* 
 * Código inspirado em Stack.java de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
    
    private class Node<Item>{
        Item item;
        Node<Item> next;
    }
    
    private Node<Item> top;
    private int size;

    // Inicialização da pilha
    public Stack(){
        top = null;
        size = 0;
    }

    // Quantidade de elementos na pilha
    public int length(){
        return size;
    }

    // Retorna se a pilha está vazia
    public boolean isEmpty(){
        return size == 0;
    }

    // Adiciona um elemento ao topo da pilha
    public void push(Item item){
        Node<Item> newtop = new Node<>();
        newtop.item = item;
        newtop.next = top;
        top = newtop;
        size++;
    }

    // Remove o elemento que se encontra no topo da pilha
    public Item pop(){
        if(size == 0) throw new NoSuchElementException("Pilha vazia");
        Item item = top.item;
        top = top.next;
        size--;
        return item;
    }

    public Item peek(){
        if(size == 0) throw new NoSuchElementException("Pilha vazia");
        Item item = top.item;
        return item;
    }

    // String representativa da pilha
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    // Iterador sobre os elementos da pilha
    public Iterator<Item> iterator() {
        return new LinkedIterator(top);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> top) {
            current = top;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
