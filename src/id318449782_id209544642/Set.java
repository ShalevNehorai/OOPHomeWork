package id318449782_id209544642;

import java.util.Iterator;
import java.util.Vector;

public class Set <T> implements Iterable<T>{
	private Vector<T> vector;

	public Set() {
		this.vector = new Vector<T>();
	}
	
	public boolean add(T obj) {
		if(contains(obj)) {
			return false;
		}
		vector.add(obj);
		return true;
	}
	
	public boolean contains(T object){
		return indexOf(object) != -1;
	}
	
	public int size() {
		return vector.size();
	}
	
	public int indexOf(T object){
		int size = this.size();
		
		for(int index = 0; index < size; index++){
			if(vector.get(index).equals(object))
				return index;
		}
		
		return -1;
	}
	public T get(int index){
		return vector.get(index);
	}
	@Override
	public Iterator<T> iterator() {
		return vector.iterator();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Set<?>)) {
			return false;
		}
		
		return vector.equals(((Set<?>)obj).vector);
	}
	
	@Override
	public String toString() {
		return vector.toString();
	}
}
